package com.whut.backproject.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Author {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("date")
    private String date;
    @TableField("name")
    private String name;
    @TableField("province")
    private String province;
    @TableField("city")
    private String city;
    @TableField("address")
    private String address;
    @TableField("zip")
    private String zip;

}