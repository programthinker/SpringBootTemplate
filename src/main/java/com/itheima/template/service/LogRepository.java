package com.itheima.template.service;

import com.itheima.template.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<Blog,String> {
}
