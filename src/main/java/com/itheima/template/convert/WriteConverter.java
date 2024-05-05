package com.itheima.template.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@WritingConverter
public class WriteConverter implements Converter<LocalDateTime, Long> {
    @Override
    public Long convert(LocalDateTime source) {
        return source.toInstant(ZoneOffset.ofHours(+8)).toEpochMilli();
    }
}
