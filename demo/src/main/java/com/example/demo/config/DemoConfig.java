package com.example.demo.config;

import com.jaspercloud.mybaits.mapper.definition.CommonMapper;
import com.jaspercloud.mybaits.mapper.support.CommonMapperFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    /**
     * 测试 CommonMapperAutoConfiguration.@ConditionalOnMissingBean(CommonMapper.class)
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public CommonMapperFactoryBean commonMapper(SqlSessionFactory sqlSessionFactory) {
        CommonMapperFactoryBean commonMapperFactoryBean = new CommonMapperFactoryBean(CommonMapper.class);
        commonMapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return commonMapperFactoryBean;
    }
}
