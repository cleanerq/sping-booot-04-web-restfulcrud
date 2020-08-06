package com.qby.spingbooot.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser != null) {
            // 已登录
            return true;
        }
        // 未登录 返回登录页面
        // 得到转发器 转发到登录页面
        request.setAttribute("msg", "没有权限请先登录！");
        request.getRequestDispatcher("/index.html").forward(request, response);

        return false;
    }
}
