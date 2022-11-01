package com.fdmgroup.employeeUI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.employeeUI.model.Employee;
import com.fdmgroup.employeeUI.service.EmployeeService;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllEmployees")
    public String getAllEmployee(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "all-employees";
    }

    @GetMapping("/getEmployee/{id}")
    public String getEmployee(Model model, @PathVariable long id) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "single-employee";
    }

//    @GetMapping("/getEmployee")    
    @PostMapping("/getEmployee")
    public String getEmployee1(Model model, @RequestParam long id) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "single-employee";
    }

    @PostMapping("/delEmployee")
    public String getEmployee3(Model model, @RequestParam long id) {
        System.out.println("here");
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "single-employee";
    }

    @GetMapping("/addNewEmployee")
    public String getEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/processAddEmployee")
    public String processAdd(Employee employee, Model model) {
        employeeService.addEmployee(employee);
        return "add-success";

    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee1(Model model, @RequestParam long id) {
        System.out.println("here----------------->>>>>>>>>>>");
        System.out.println("attempting to del");
        employeeService.deleteEmployee(id);
        return "delete-success";
    }

}
