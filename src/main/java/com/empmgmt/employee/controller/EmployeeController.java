package com.empmgmt.employee.controller;

import com.empmgmt.employee.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    List<Employee> employees = List.of(new Employee(1,"Akash", 20000, "IT"),
            new Employee(2, "Rohit", 45000, "Development"));

    //http://localhost:8080/api/employees
    @RequestMapping
    public List<Employee> getEmployees(){
        System.out.println("inside first getEmployee");
        return employees;
    }

    //http://localhost:8080/api/employees/emp
    @RequestMapping("/emp")
    public List<Employee> getEmployees1(){
        System.out.println("inside second getEmployee");
        return employees;
    }
}
