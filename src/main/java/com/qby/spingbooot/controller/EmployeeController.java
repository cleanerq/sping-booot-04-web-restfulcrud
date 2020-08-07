package com.qby.spingbooot.controller;

import com.qby.spingbooot.dao.DepartmentDao;
import com.qby.spingbooot.dao.EmployeeDao;
import com.qby.spingbooot.entities.Department;
import com.qby.spingbooot.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    /**
     * 员工添加保存
     * 自动将请求参数与入参对象绑定
     *
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);

        logger.info("保存的员工信息:{}", employee.toString());
        // /代表当前项目路径
        return "redirect:/emps";
    }

    // 来到修改页面，查询当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        // 查询所有部门在页面显示用
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        // 修改和添加用一个页面
        return "emp/add";
    }

    // 员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {

        employeeDao.save(employee);

        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model) {
        employeeDao.delete(id);

        return "redirect:/emps";
    }
}
