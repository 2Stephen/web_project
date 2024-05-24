package com.whut.backproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.github.pagehelper.Page;
import com.github.pagehelper.Page;
import com.whut.backproject.entity.Author;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

    int countByName(String name);

    List<Author> selectAll();

    List<Author> selectByName(String name);
}
