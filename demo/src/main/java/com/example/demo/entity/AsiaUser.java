package com.example.demo.entity;

import javax.persistence.Entity;

@Entity(name = "t_asia_user")
public class AsiaUser extends User {

    private String province;
    private String city;

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

    public AsiaUser() {
    }
}
