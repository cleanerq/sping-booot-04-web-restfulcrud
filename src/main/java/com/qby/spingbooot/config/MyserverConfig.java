package com.qby.spingbooot.config;

import com.qby.spingbooot.filter.MyFilter;
import com.qby.spingbooot.listen.MyListener;
import com.qby.spingbooot.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class MyserverConfig implements WebMvcConfigurer {

    // 配置嵌入式的servlet容器
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                // 定制嵌入式servlet容器的相关规则
                factory.setPort(8080);
            }
        };
    }

    // 注册三大组件

    // 注册servlet
    @Bean
    public ServletRegistrationBean myservlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new MyServlet(),
                "/myServlet");
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<MyListener> myListener() {
        ServletListenerRegistrationBean<MyListener> listener =
                new ServletListenerRegistrationBean<>(new MyListener());
        return listener;
    }


    @Bean
    public FilterRegistrationBean myFilterFilterRegistrationBean() {
        FilterRegistrationBean filterFilterRegistrationBean = new FilterRegistrationBean<>();

        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/myServlet"));

        return filterFilterRegistrationBean;
    }
}
