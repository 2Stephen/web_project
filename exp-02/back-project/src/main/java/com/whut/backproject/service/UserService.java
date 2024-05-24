package com.whut.backproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.whut.backproject.POJO.UserInfo;
import com.whut.backproject.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> selectAll();
    boolean authenticate(String username, String password);

    boolean register(String username, String password, String email, String birthday, String avatarUrl);

    boolean changePassword(String username, String password, String newPassword);

    UserInfo getUserByUsername(String username);
}
