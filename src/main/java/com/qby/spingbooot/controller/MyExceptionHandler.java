package com.qby.spingbooot.controller;

import com.qby.spingbooot.exception.UserNotExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 */
@ControllerAdvice
public class MyExceptionHandler {

//    1、浏览器和客户端都是返回json
//    @ResponseBody
//    @ExceptionHandler(UserNotExistsException.class)
//    public Map<String, Object> handleException(Exception e) {
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("code", "user.notexists");
//        map.put("message", e.getMessage());
//        return map;
//    }

    // 2、转发到 /error 请求由 BasicErrorController 进行自适应处理
    @ExceptionHandler(UserNotExistsException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        // 传入我们自己的错误状态码 4xx 5xx
        // String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexists");
        map.put("message", e.getMessage());

        request.setAttribute("ext", map);
        // 转发到 /error 请求由 BasicErrorController 进行自适应处理
        return "forward:/error";
    }

}
