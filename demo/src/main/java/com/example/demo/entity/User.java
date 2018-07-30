package com.example.demo.entity;

import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Cacheable 是否缓存
 */
@Cacheable
/**
 * 表名映射
 */
@Entity(name = "t_user")
public class User {

    /**
     * 主键生成
     */
    @GeneratedValue(generator = "select nextval('seq_user')")
    /**
     * 主键
     */
    @Id
    /**
     * 字段映射
     */
    @Column
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

    private String sex;

    @Column(name = "user_mark")
    private String userMark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    public User() {
    }
}
