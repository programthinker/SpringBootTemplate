package com.itheima.template.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itheima.template.config.LocalDateTimeDeserializer;
import com.itheima.template.config.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
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
@Document(indexName = "#{@dynamicIndex.getIndex()}",createIndex = false)
public class Blog {

    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Text)
    private String authorName;

    @Field(type = FieldType.Keyword)
    private String authorId;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Field(type = FieldType.Date,pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creatTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Field(type = FieldType.Date,pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;
}
