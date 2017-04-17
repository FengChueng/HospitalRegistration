package com.zyl.hospital.registration.data.remote.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

/**
 * Created by cjb
 */


public class User extends BmobUser implements Serializable{
    private Object object;
    private Integer role;//0表示病人，1表示医生

    public User() {
    }

    public User(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
