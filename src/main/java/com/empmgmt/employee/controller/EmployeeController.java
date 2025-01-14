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
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) { //104
        //System.out.println("id : "+id);
//        Employee emp = null;
//        for (Employee employee : employees) {
//            if (employee.getId() == id) { //101,102,103,104
//                 emp = employee;
//            }
//        }
//        return emp;
        return employees.stream().filter(emp -> emp.getId() == id).findFirst().orElse(new Employee());
    }
}
