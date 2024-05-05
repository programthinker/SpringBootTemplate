package com.itheima.template.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @ProjectName mybatis-plus-test
 * @PackageName com.example.mybatisplustest.entity
 * @ClassName Blog
 * @Author zhanggeyang
 * @Date 2023-03-31 16:38
 * @Description
 * @Version 1.0
 */

@Data
public class Blog1 {

    private String id;

    private String title;

    private String content;

    private String authorName;

    private String authorId;

//    @JsonFormat(shape = JsonFormat.Shape.NUMBER,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
//    @Field(type = FieldType.Date,pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creatTime;

//    @JsonFormat(shape = JsonFormat.Shape.NUMBER,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
//    @Field(type = FieldType.Date,pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;
}
