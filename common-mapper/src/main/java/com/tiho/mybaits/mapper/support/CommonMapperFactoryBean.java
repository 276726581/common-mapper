package com.tiho.mybaits.mapper.support;

import com.tiho.mybaits.mapper.config.CommonMapperProxy;
import com.tiho.mybaits.mapper.definition.CommonMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

public class CommonMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private SqlSessionFactory sqlSessionFactory;
    private Class<T> clazz;
    private T mapper;

    public CommonMapperFactoryBean(SqlSessionFactory sqlSessionFactory) {
        this(sqlSessionFactory, (Class<T>) CommonMapper.class);
    }

    public CommonMapperFactoryBean(SqlSessionFactory sqlSessionFactory, Class<T> clazz) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.clazz = clazz;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ClassLoader classLoader = clazz.getClassLoader();
        CommonMapperProxy commonMapperProxy = new CommonMapperProxy(sqlSessionFactory, clazz);
        mapper = (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, commonMapperProxy);
    }

    @Override
    public T getObject() throws Exception {
        return mapper;
    }

    @Override
    public Class<?> getObjectType() {
        return CommonMapper.class;
    }
}
