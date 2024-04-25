package com.whut.backproject.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterData {
    private String username;

    private String password;

    private String email;

    private String birthday;

    public RegisterData(){

    }
    public RegisterData(String username,String password,String email,String birthday){
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }
    @Override
    public String toString()
    {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
