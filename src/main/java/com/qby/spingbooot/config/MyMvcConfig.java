package com.qby.spingbooot.config;

import com.qby.spingbooot.component.LoginHandlerInterceptor;
import com.qby.spingbooot.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    // 所有的WebMvcConfigurer组件都会一起起作用
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer config = new WebMvcConfigurer() {
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                // 重定向
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };

        return config;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }


    /**
     * 权限验证拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截任意请求
        // 登录页面不拦截
        // 静态资源 *.css *.js
        // springboot 已经做好静态资源映射 可以不用处理经停资源
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html",
                        "/user/login",
                        "/",
                        "/static/**",
                        "/webjars/**",
                        "/asserts/**",
                        "/hello",
                        "/myServlet",
                        "/public/**");
    }

}
