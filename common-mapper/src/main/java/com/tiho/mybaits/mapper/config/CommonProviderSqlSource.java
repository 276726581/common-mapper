package com.tiho.mybaits.mapper.config;

import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonProviderSqlSource extends ProviderSqlSource {

    private Configuration configuration;
    private EntityConfig entityConfig;

    private static Map<Class<? extends TypeHandler<?>>, TypeHandler<?>> typeHandlerMap = new ConcurrentHashMap<>();

    public CommonProviderSqlSource(Configuration configuration, Object provider, Class<?> mapperType, Method mapperMethod, EntityConfig entityConfig) {
        super(configuration, provider, mapperType, mapperMethod);
        this.configuration = configuration;
        this.entityConfig = entityConfig;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        BoundSql boundSql = super.getBoundSql(parameterObject);
        Map<String, Field> fieldNameMap = entityConfig.getFieldNameMap();
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String property = parameterMapping.getProperty();
            String fieldName = property.replace("param.", "");
            Field field = fieldNameMap.get(fieldName);
            if (null != field) {
                TypeHandler<?> typeHandler = cacheTypeHandler(field, parameterMapping);
                ParameterMapping mapping = new ParameterMapping.Builder(configuration, property, field.getType())
                        .mode(parameterMapping.getMode())
                        .numericScale(parameterMapping.getNumericScale())
                        .resultMapId(parameterMapping.getResultMapId())
                        .expression(parameterMapping.getExpression())
                        .typeHandler(typeHandler)
                        .build();
                parameterMappingList.set(i, mapping);
            }
        }
        return boundSql;
    }

    private TypeHandler<?> cacheTypeHandler(Field field, ParameterMapping parameterMapping) {
        Map<Field, Class<? extends TypeHandler<?>>> fieldTypeHandlerMap = entityConfig.getFieldTypeHandlerMap();
        Class<? extends TypeHandler<?>> clazz = fieldTypeHandlerMap.get(field);
        if (null == clazz) {
            return parameterMapping.getTypeHandler();
        }

        TypeHandler<?> typeHandler = typeHandlerMap.get(clazz);
        if (null == typeHandler) {
            try {
                typeHandler = clazz.newInstance();
                typeHandlerMap.put(clazz, typeHandler);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e.getMessage(), e);
            }
        }
        return typeHandler;
    }
}
