package com.qby.spingbooot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用 WebMvcConfigurer 可以扩展springmvc
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 添加一个简单视图映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 把 /qby 请求映射到 success 浏览器 发送 /qby 也来到 success页面
        registry.addViewController("/qby").setViewName("success");
    }
}
