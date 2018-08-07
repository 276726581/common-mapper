package com.example.demo.entity;

import com.example.demo.support.ImageListTypeHandler;
import com.example.demo.support.MapTypeHandler;
import com.jaspercloud.mybaits.mapper.annotation.DataSource;
import com.jaspercloud.mybaits.mapper.annotation.FieldTypeDiscriminator;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Cacheable 是否缓存
 */
@Cacheable
@DataSource
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

    @FieldTypeDiscriminator(javaType = String.class, typeHandler = ImageListTypeHandler.class)
    @Column(name = "images")
    private List<String> imagesList = new ArrayList<>();

    @FieldTypeDiscriminator(javaType = String.class, typeHandler = MapTypeHandler.class)
    private Map<String, String> params = new HashMap<>();

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

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void addImage(String image) {
        imagesList.add(image);
    }

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public User() {
    }
}
