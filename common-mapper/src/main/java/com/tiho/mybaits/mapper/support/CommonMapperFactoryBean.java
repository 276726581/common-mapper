package com.tiho.mybaits.mapper.support;

import com.tiho.mybaits.mapper.definition.CommonMapper;
import com.tiho.mybaits.mapper.config.CommonMapperProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

public class CommonMapperFactoryBean implements FactoryBean<CommonMapper>, InitializingBean {

    private SqlSessionFactory sessionFactory;
    private CommonMapper commonMapper;

    public CommonMapperFactoryBean(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Class clazz = CommonMapper.class;
        CommonMapperProxy commonMapperProxy = new CommonMapperProxy(sessionFactory, clazz);
        ClassLoader classLoader = clazz.getClassLoader();
        commonMapper = (CommonMapper) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, commonMapperProxy);
    }

    @Override
    public CommonMapper getObject() throws Exception {
        return commonMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return CommonMapper.class;
    }
}
