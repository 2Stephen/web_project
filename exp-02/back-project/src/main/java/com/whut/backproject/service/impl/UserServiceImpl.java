package com.whut.backproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whut.backproject.entity.User;
import com.whut.backproject.mapper.UserMapper;
import com.whut.backproject.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> selectAll(){
        return userMapper.selectAll();
    }
    @Override
    public boolean authenticate(String username, String password) {
        User user = userMapper.findByName(username);
        return user != null && user.getPassword() != null && user.getPassword().equals(password);
    }

    @Override
    public boolean register(String username, String password, String email, String birthday) {
        User user = userMapper.findByName(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setBirthday(birthday);
            userMapper.insert(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(String username, String password, String newPassword) {
        User user = userMapper.findByName(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setPassword(newPassword);
            userMapper.updateById(user);
            System.out.println(user.getPassword());
            return true;
        }
        return false;
    }

}
