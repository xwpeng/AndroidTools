package com.android.xwpeng.tgson.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xwpeng on 2018/7/11.
 */

public class User {
    public String name;
    public int age;
    @SerializedName(value = "email", alternate = {"email_address", "emailAddress"})
    public String emailAddress;

    public User(String name, int age, String emailAddress) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
