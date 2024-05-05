package com.itheima.template.config;

import org.springframework.stereotype.Component;

@Component(value = "dynamicIndex")
public class DynamicIndex {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取索引名称后缀
     *
     * @return
     */
    public String getIndex() {
        return THREAD_LOCAL.get();
    }

    /**
     * 设置索引名称后缀
     *
     * @param suffix
     */
    public void setIndex(String suffix) {
        THREAD_LOCAL.set(suffix);
    }

    /**
     * 移除当前索引
     */
    public void remove() {
        THREAD_LOCAL.remove();
    }
}
