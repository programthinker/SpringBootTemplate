package com.itheima.template.controller;

import com.itheima.template.entity.Blog;
import com.itheima.template.entity.Blog1;
import com.itheima.template.entity.BlogQueryDTO;
import com.itheima.template.service.SearchService;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/es")
public class SearchController {

    @Resource
    private SearchService searchService;

    @PostMapping(value = "/insert")
    public void insert(@RequestBody Blog blog) {
        searchService.insert(blog);
    }

    @PostMapping(value = "/get")
    public void get(@RequestBody Blog blog) {
        searchService.selectById(blog.getId());
    }

    @PostMapping(value = "/list")
    public List<Blog1> list(@RequestBody BlogQueryDTO blog) {
        return searchService.selectList(blog);
    }
}
