package com.tiho.mybaits.mapper.autoconfig;

import com.tiho.mybaits.mapper.definition.CommonMapper;
import com.tiho.mybaits.mapper.support.CommonMapperFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonMapperAutoConfiguration {

    @ConditionalOnMissingBean(CommonMapper.class)
    @Bean
    public CommonMapperFactoryBean commonMapper(SqlSessionFactory sqlSessionFactory) {
        CommonMapperFactoryBean commonMapperFactoryBean = new CommonMapperFactoryBean(sqlSessionFactory);
        return commonMapperFactoryBean;
    }
}
