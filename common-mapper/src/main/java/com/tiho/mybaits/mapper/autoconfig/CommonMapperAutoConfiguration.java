package com.tiho.mybaits.mapper.autoconfig;

import com.tiho.mybaits.mapper.definition.CommonMapper;
import com.tiho.mybaits.mapper.support.CommonMapperFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonMapperAutoConfiguration {

    @ConditionalOnMissingBean(CommonMapper.class)
    @Bean
    public CommonMapperFactoryBean commonMapper(@Autowired(required = false) SqlSessionFactory sqlSessionFactory) {
        CommonMapperFactoryBean commonMapperFactoryBean = new CommonMapperFactoryBean(CommonMapper.class);
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        commonMapperFactoryBean.setSqlSessionTemplate(sqlSessionTemplate);
        return commonMapperFactoryBean;
    }
}
