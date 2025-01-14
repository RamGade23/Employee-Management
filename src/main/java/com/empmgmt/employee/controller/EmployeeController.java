package com.empmgmt.employee.controller;

import com.empmgmt.employee.entity.Employee;
import com.empmgmt.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServices;
    List<Employee> employees = new ArrayList<>(); //replace by database

    // api/employees
    @PostMapping
    public String addEmployee(@RequestBody Employee employee) {
        System.out.println("input request : " + employee.toString());
        //employees.add(employee);
        Employee savedEmployee = employeeServices.saveEmployee(employee);
        return "Employee inserted successfully with id : " + savedEmployee.getId();
    }

    @GetMapping
    public List<Employee> getEmployees() {
        System.out.println("inside getEmployee method");
        return employeeServices.getEmployees();
    }

    @GetMapping("/{id}") //api/employees/8
    public Employee getEmployeeById(@PathVariable long id) {//id =7
        return employeeServices.getEmployeeById(id);
    }
}
