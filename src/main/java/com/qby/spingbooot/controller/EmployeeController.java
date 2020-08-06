package com.qby.spingbooot.controller;

import com.qby.spingbooot.dao.DepartmentDao;
import com.qby.spingbooot.dao.EmployeeDao;
import com.qby.spingbooot.entities.Department;
import com.qby.spingbooot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    // 查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model) {

        Collection<Employee> all = employeeDao.getAll();

        model.addAttribute("emps", all);
        // thymeleaf 拼串
        // 前缀 classpath:/templates/
        // 后缀 .html
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面
        // 查询所有部门在页面显示用

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }
}
