package com.whut.backproject.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorData {
    private String date;

    private String name;

    private String province;

    private String city;

    private String address;

    private String zip;

    public AuthorData(){

    }
    public AuthorData(String date,String name,String province,String city,String address,String zip){
        this.date = date;
        this.name = name;
        this.province = province;
        this.city = city;
        this.address = address;
        this.zip = zip;
    }
    @Override
    public String toString()
    {
        return "AuthorData{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
