package com.whut.backproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.backproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();
    User findByName(String username);
}
