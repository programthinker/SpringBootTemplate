package com.itheima.template.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ReadingConverter
public class ReadConverter implements Converter<Long, LocalDateTime> {
    @Override
    public LocalDateTime convert(Long source) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(source), ZoneOffset.UTC);
    }
}
