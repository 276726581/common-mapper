package com.example.demo;

import com.example.demo.entity.User;
import com.jaspercloud.mybaits.mapper.autoconfig.CommonMapperAutoConfiguration;
import com.jaspercloud.mybaits.mapper.definition.CommonMapper;
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

//        User user = new User();
//        user.setUserName("test");
//        user.setPassWord("test");
//        user.setSex("男");
//        user.setUserMark("test");
//        user.addImage("http");
//        user.addImage("https");
//        user.addParam("test", "test");
//        commonMapper.insert(User.class, user);
//        System.out.println();

        List<User> userList = commonMapper.selectAll(User.class);
        User u = commonMapper.selectOneById(User.class, 460L);
        long count = commonMapper.count(User.class);
        System.out.println();
//
//        Long newId;
//        {
//            User user = new User();
//            user.setUserName("test");
//            user.setPassWord("test");
//            user.setSex("男");
//            user.setUserMark("test");
//            commonMapper.insert(User.class, user);
//            newId = user.getId();
//        }
//        Long updateId;
//        {
//            User user = new User();
//            user.setId(422L);
//            user.setUserName("testtest");
//            user.setPassWord("testtest");
//            user.setSex("男");
//            user.setUserMark("test1");
//            commonMapper.updateNotNullById(User.class, user);
//            updateId = user.getId();
//        }
//        {
//            commonMapper.deleteById(User.class, newId);
//        }
//        {
//            User user = new User();
//            user.setUserName("testtest");
//            user.setPassWord("testtest");
//            commonMapper.delete(User.class, user);
//        }
//        {
//            User user = commonMapper.selectOneById(User.class, 1L);
//            System.out.println();
//        }
//        {
//            User user = new User();
//            user.setUserName("test2");
//            user.setPassWord("test2");
//            User result = commonMapper.selectOne(User.class, user);
//            System.out.println();
//        }
//        {
//            User user = new User();
//            user.setUserName("test3");
//            user.setPassWord("test3");
//            List<User> results = commonMapper.select(User.class, user);
//            System.out.println();
//        }
//        /*****/
//        {
//            AsiaUser user = new AsiaUser();
//            user.setUserName("test");
//            user.setPassWord("test");
//            user.setSex("男");
//            user.setUserMark("test");
//            user.setProvince("test");
//            user.setCity("test");
//            user.setMark("test");
//            commonMapper.insert(AsiaUser.class, user);
//        }
//        {
//            AsiaUser user = new AsiaUser();
//            user.setUserName("test");
//            user.setPassWord("test");
//            user.setSex("男");
//            user.setUserMark("test");
//            commonMapper.updateNotNullById(AsiaUser.class, user);
//        }
//        {
//            commonMapper.deleteById(AsiaUser.class, 1L);
//        }
//        {
//            AsiaUser user = new AsiaUser();
//            user.setUserName("testt");
//            user.setPassWord("testt");
//            commonMapper.delete(AsiaUser.class, user);
//        }
//        {
//            AsiaUser user = commonMapper.selectOneById(AsiaUser.class, 1L);
//            System.out.println();
//        }
//        {
//            AsiaUser user = new AsiaUser();
//            user.setUserName("test2");
//            user.setPassWord("test2");
//            AsiaUser result = commonMapper.selectOne(AsiaUser.class, user);
//            System.out.println();
//        }
//        {
//            AsiaUser user = new AsiaUser();
//            user.setUserName("test3");
//            user.setPassWord("test3");
//            List<AsiaUser> results = commonMapper.select(AsiaUser.class, user);
//            System.out.println();
//        }
//        {
//            long count = commonMapper.count(User.class);
//            System.out.println();
//        }
//        {
//            AsiaUser user = new AsiaUser();
//            user.setUserName("test");
//            user.setPassWord("test");
//            long count = commonMapper.countWhere(AsiaUser.class, user);
//            System.out.println();
//        }
//        System.out.println();
    }
}
