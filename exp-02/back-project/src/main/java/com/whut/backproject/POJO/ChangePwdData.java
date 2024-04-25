package com.whut.backproject.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePwdData {
    private String username;

    private String password;

    private String newPassword;

    public ChangePwdData(){

    }
    public ChangePwdData(String username,String password,String newPassword){
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
    }
    @Override
    public String toString()
    {
        return "ChangePwdData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
