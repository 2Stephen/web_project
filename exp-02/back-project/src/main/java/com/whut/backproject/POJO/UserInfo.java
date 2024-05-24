package com.whut.backproject.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String username;

    private String email;

    private String birthday;

    private double balance;

    private String avatar;
    public UserInfo(){

    }
    public UserInfo(String username,String email,String birthday,int balance ,String avatar){
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.balance = balance;
        this.avatar = avatar;
    }
    @Override
    public String toString()
    {
        return "UserData{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", balance='" + balance + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String get() {
        return "{" +
                "\"username\":\"" + username + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"birthday\":\"" + birthday + '\"' +
                ", \"balance\":\"" + balance + '\"' +
                ", \"avatar\":\"" + avatar + '\"' +
                '}';
    }
}
