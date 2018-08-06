package com.tiho.mybaits.mapper.support;

import com.tiho.mybaits.mapper.config.CommonMapperProxy;
import com.tiho.mybaits.mapper.definition.CommonMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

public class CommonMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean {

    private SqlSessionFactory sqlSessionFactory;
    private Class<T> clazz;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public CommonMapperFactoryBean(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public T getObject() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonMapperProxy commonMapperProxy = new CommonMapperProxy(sqlSession, clazz);
        T mapper = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, commonMapperProxy);
        return mapper;
    }

    @Override
    public Class<?> getObjectType() {
        return CommonMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
