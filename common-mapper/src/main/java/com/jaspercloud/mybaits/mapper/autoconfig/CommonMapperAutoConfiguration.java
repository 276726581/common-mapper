package com.jaspercloud.mybaits.mapper.autoconfig;

import com.jaspercloud.mybaits.mapper.definition.CommonMapper;
import com.jaspercloud.mybaits.mapper.support.CommonMapperFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonMapperAutoConfiguration {

    @ConditionalOnMissingBean(CommonMapper.class)
    @Bean
    public CommonMapperFactoryBean commonMapper(SqlSessionFactory sqlSessionFactory) {
        CommonMapperFactoryBean commonMapperFactoryBean = new CommonMapperFactoryBean(CommonMapper.class);
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        commonMapperFactoryBean.setSqlSessionTemplate(sqlSessionTemplate);
        return commonMapperFactoryBean;
    }
}
