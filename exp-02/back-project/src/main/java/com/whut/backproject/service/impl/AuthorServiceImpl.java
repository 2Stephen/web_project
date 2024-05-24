package com.whut.backproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.backproject.entity.Author;
import com.whut.backproject.mapper.AuthorMapper;
import com.whut.backproject.service.AuthorService;
import org.springframework.stereotype.Service;
//import com.github.pagehelper.Page;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

    @Resource
    private AuthorMapper authorMapper;

    @Override
    public int countByName(String name) {
        return authorMapper.countByName(name);
    }

    @Override
    public void addAuthor(String date, String name, String province, String city, String address, String zip) {
        Author author = new Author();
        if (name != null && authorMapper.countByName(name) == 0) {
            author.setDate(date);
            author.setName(name);
            author.setProvince(province);
            author.setCity(city);
            author.setAddress(address);
            author.setZip(zip);
            authorMapper.insert(author);
        }
    }

    @Override
    public PageInfo<Author> getAuthors(int page, int pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);
        // 查询结果
        List<Author> authors = authorMapper.selectAll();
        // 返回分页信息
        return new PageInfo<>(authors);
    }

    @Override
    public PageInfo<Author> searchAuthors(String name) {
        // 查询结果
        List<Author> authors = authorMapper.selectByName(name);
        return new PageInfo<>(authors);
    }


}
