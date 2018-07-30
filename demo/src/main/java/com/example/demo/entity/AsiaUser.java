package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name = "t_asia_user")
public class AsiaUser extends User {

    private String province;
    private String city;

    @Transient
    private transient String mark;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public AsiaUser() {
    }
}
