package com.itheima.template.service;

import com.itheima.template.entity.Blog;
import com.itheima.template.entity.Blog1;
import com.itheima.template.entity.BlogQueryDTO;

import java.util.List;

public interface SearchService  {

    void insert (Blog blog);
    void update (Blog blog);
    Blog selectById (String id);

    List<Blog1> selectList(BlogQueryDTO blog);

    Blog get(Blog blog);
}
