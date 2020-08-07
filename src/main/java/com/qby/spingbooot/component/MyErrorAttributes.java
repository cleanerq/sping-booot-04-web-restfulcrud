package com.qby.spingbooot.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

// 给容器中放入自定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    // 返回值的map就是页面和json能获取到的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        map.put("company", "caissa");
        // 从请求域中取值
        // 异常处理器携带的数据
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        map.put("ext", ext);
//        map.putAll(ext);

        return map;
    }
}
