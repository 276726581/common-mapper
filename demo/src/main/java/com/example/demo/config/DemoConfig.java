package com.example.demo.config;

import com.tiho.mybaits.mapper.support.CommonMapperFactoryBean;
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
        CommonMapperFactoryBean commonMapperFactoryBean = new CommonMapperFactoryBean(sqlSessionFactory);
        return commonMapperFactoryBean;
    }
}
