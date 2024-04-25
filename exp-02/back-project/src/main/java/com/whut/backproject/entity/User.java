package com.whut.backproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("birthday")
    private String birthday;
    @TableField("email")
    private String email;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
}
