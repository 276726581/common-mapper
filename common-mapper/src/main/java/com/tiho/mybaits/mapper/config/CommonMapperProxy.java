package com.tiho.mybaits.mapper.config;

import com.tiho.mybaits.mapper.util.CommonMapperContext;
import com.tiho.mybaits.mapper.util.ReflectUtils;
import org.apache.ibatis.lang.UsesJava7;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonMapperProxy implements InvocationHandler {

    private final Map<Class<?>, EntityConfig> entityConfigMap = new ConcurrentHashMap<>();
    private final Map<String, MapperMethod> methodCache = new ConcurrentHashMap<>();
    private final SqlSessionFactory sqlSessionFactory;
    private Class mapperInterface;

    public CommonMapperProxy(SqlSessionFactory sqlSessionFactory, Class mapperInterface) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            } else if (isDefaultMethod(method)) {
                return invokeDefaultMethod(proxy, method, args);
            }
        } catch (Throwable t) {
            throw ExceptionUtil.unwrapThrowable(t);
        }
        try {
            Class<?> entity = (Class<?>) args[0];
            EntityConfig entityConfig = cacheEntityConfig(entity);
            CommonMapperContext commonMapperContext = CommonMapperContext.get();
            commonMapperContext.setEntityConfig(entityConfig);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            MapperMethod mapperMethod = cachedMapperMethod(method, entity);
            return mapperMethod.execute(sqlSession, args);
        } finally {
            CommonMapperContext.remove();
        }
    }

    private EntityConfig cacheEntityConfig(Class<?> clazz) {
        EntityConfig entityConfig = entityConfigMap.get(clazz);
        if (null == entityConfig) {
            Entity entity = clazz.getAnnotation(Entity.class);
            if (null == entity) {
                throw new NullPointerException("not found @Entity");
            }

            entityConfig = new EntityConfig();
            Cacheable cacheable = ReflectUtils.getAnnotation(clazz, Cacheable.class);
            entityConfig.setCache(null != cacheable ? true : false);
            entityConfig.setEntity(clazz);
            entityConfig.setTableName(entity.name());
            Field[] fields = ReflectUtils.getAllDeclaredFields(clazz);
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                Id id = field.getAnnotation(Id.class);
                GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
                Transient transientAnnotation = field.getAnnotation(Transient.class);
                String modifier = Modifier.toString(field.getModifiers());
                if (null != transientAnnotation || modifier.contains("transient")) {
                    continue;
                }
                if (null != column && !StringUtils.isEmpty(column.name())) {
                    entityConfig.addColumnField(field, column.name());
                } else {
                    entityConfig.addColumnField(field, field.getName());
                }
                if (null != id) {
                    if (null != column && !StringUtils.isEmpty(column.name())) {
                        entityConfig.setId(column.name());
                    } else {
                        entityConfig.setId(field.getName());
                    }
                }
                if (null != generatedValue) {
                    entityConfig.addSequenceField(field, generatedValue.generator());
                }
            }
            entityConfigMap.put(clazz, entityConfig);

            Configuration configuration = sqlSessionFactory.getConfiguration();
            CommonMapperAnnotationBuilder commonMapperAnnotationBuilder = new CommonMapperAnnotationBuilder(configuration, mapperInterface, entityConfig);
            commonMapperAnnotationBuilder.parse();
        }
        return entityConfig;
    }

    private MapperMethod cachedMapperMethod(Method method, Class<?> entity) {
        String key = method.getDeclaringClass().getName() + "." + method.getName() + "-" + entity.getName();
        MapperMethod mapperMethod = methodCache.get(key);
        if (mapperMethod == null) {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            mapperMethod = new MapperMethod(mapperInterface, method, configuration, entity);
            methodCache.put(key, mapperMethod);
        }
        return mapperMethod;
    }

    @UsesJava7
    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args)
            throws Throwable {
        final Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class
                .getDeclaredConstructor(Class.class, int.class);
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        final Class<?> declaringClass = method.getDeclaringClass();
        return constructor
                .newInstance(declaringClass,
                        MethodHandles.Lookup.PRIVATE | MethodHandles.Lookup.PROTECTED
                                | MethodHandles.Lookup.PACKAGE | MethodHandles.Lookup.PUBLIC)
                .unreflectSpecial(method, declaringClass).bindTo(proxy).invokeWithArguments(args);
    }

    /**
     * Backport of java.lang.reflect.Method#isDefault()
     */
    private boolean isDefaultMethod(Method method) {
        return (method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
                && method.getDeclaringClass().isInterface();
    }
}
