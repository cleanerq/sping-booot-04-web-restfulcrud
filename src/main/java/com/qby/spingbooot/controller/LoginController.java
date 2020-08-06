package com.qby.spingbooot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            // 登录成功，防止表单重复提交，可以重定向到主页
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户名密码错误！");
            return "login";
        }
    }

//    @RequestMapping("/dashboard")
//    public String dashboard() {
//        return "dashboard";
//    }
}
