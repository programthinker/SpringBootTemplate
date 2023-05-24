package com.itheima.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ProjectName SpringBootTemplate
 * @PackageName com.itheima.template
 * @ClassName Application
 * @Author zhanggeyang
 * @Date 2023-05-24 21:14
 * @Description
 * @Version 1.0
 */

@EnableAsync
@SpringBootApplication
@MapperScan(value = {"com.itheima.template.mapper"})
@EnableScheduling
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
