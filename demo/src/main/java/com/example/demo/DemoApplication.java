package com.example.demo;

import com.example.demo.entity.AsiaUser;
import com.example.demo.entity.User;
import com.tiho.mybaits.mapper.autoconfig.CommonMapperAutoConfiguration;
import com.tiho.mybaits.mapper.definition.CommonMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@ImportAutoConfiguration(CommonMapperAutoConfiguration.class)
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = springApplication.run(args);
        SqlSessionFactory sessionFactory = context.getBean(SqlSessionFactory.class);
//        CommonMapperFactoryBean commonMapperFactoryBean = new CommonMapperFactoryBean(sessionFactory);
//        commonMapperFactoryBean.afterPropertiesSet();
//        CommonMapper commonMapper = commonMapperFactoryBean.getObject();
        CommonMapper commonMapper = context.getBean(CommonMapper.class);
        System.out.println();

        {
            User user = new User();
            user.setUserName("test");
            user.setPassWord("test");
            user.setSex("男");
            user.setUserMark("test");
            commonMapper.insert(User.class, user);
        }
        {
            User user = new User();
            user.setUserName("test");
            user.setPassWord("test");
            user.setSex("男");
            user.setUserMark("test");
            commonMapper.updateNotNullById(User.class, user);
        }
        {
            commonMapper.deleteById(User.class, 1L);
        }
        {
            User user = new User();
            user.setUserName("testt");
            user.setPassWord("testt");
            commonMapper.delete(User.class, user);
        }
        {
            User user = commonMapper.selectOneById(User.class, 1L);
            System.out.println();
        }
        {
            User user = new User();
            user.setUserName("test2");
            user.setPassWord("test2");
            User result = commonMapper.selectOne(User.class, user);
            System.out.println();
        }
        {
            User user = new User();
            user.setUserName("test3");
            user.setPassWord("test3");
            List<User> results = commonMapper.select(User.class, user);
            System.out.println();
        }
        /*****/
        {
            AsiaUser user = new AsiaUser();
            user.setUserName("test");
            user.setPassWord("test");
            user.setSex("男");
            user.setUserMark("test");
            user.setProvince("test");
            user.setCity("test");
            commonMapper.insert(AsiaUser.class, user);
        }
        {
            AsiaUser user = new AsiaUser();
            user.setUserName("test");
            user.setPassWord("test");
            user.setSex("男");
            user.setUserMark("test");
            commonMapper.updateNotNullById(AsiaUser.class, user);
        }
        {
            commonMapper.deleteById(AsiaUser.class, 1L);
        }
        {
            AsiaUser user = new AsiaUser();
            user.setUserName("testt");
            user.setPassWord("testt");
            commonMapper.delete(AsiaUser.class, user);
        }
        {
            AsiaUser user = commonMapper.selectOneById(AsiaUser.class, 1L);
            System.out.println();
        }
        {
            AsiaUser user = new AsiaUser();
            user.setUserName("test2");
            user.setPassWord("test2");
            AsiaUser result = commonMapper.selectOne(AsiaUser.class, user);
            System.out.println();
        }
        {
            AsiaUser user = new AsiaUser();
            user.setUserName("test3");
            user.setPassWord("test3");
            List<AsiaUser> results = commonMapper.select(AsiaUser.class, user);
            System.out.println();
        }

        System.out.println();
    }
}
