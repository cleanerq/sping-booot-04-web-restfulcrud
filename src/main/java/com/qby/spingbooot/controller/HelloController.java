package com.qby.spingbooot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Model model) {
        model.addAttribute("hello", "hello");
        List<String> list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        model.addAttribute("users", list);

        return "success";
    }
}
