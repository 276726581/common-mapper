package com.jaspercloud.mybaits.mapper.support;

import com.jaspercloud.mybaits.mapper.config.CommonMapperProxy;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

public class CommonMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private SqlSessionTemplate sqlSessionTemplate;
    private Class<T> clazz;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public CommonMapperFactoryBean(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public T getObject() throws Exception {
        CommonMapperProxy commonMapperProxy = new CommonMapperProxy(sqlSessionTemplate, clazz);
        T mapper = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, commonMapperProxy);
        return mapper;
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
