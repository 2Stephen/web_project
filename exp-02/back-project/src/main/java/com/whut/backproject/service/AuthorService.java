package com.whut.backproject.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.whut.backproject.entity.Author;
import com.github.pagehelper.PageInfo;

public interface AuthorService extends IService<Author> {
    int countByName(String name);

    void addAuthor(String date, String name, String province, String city, String address, String zip);

    PageInfo<Author> getAuthors(int page, int pageSize);

    PageInfo<Author> searchAuthors(String name);
}
