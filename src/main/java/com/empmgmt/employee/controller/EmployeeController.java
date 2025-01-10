package com.empmgmt.employee.controller;

import com.empmgmt.employee.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    List<Employee> employees = List.of(
            new Employee(101,"Akash", 20000, "IT"),
            new Employee(102, "Rohit", 45000, "Development"),
            new Employee(103, "Rushi", 35000, "Development"),
            new Employee(104, "Praful", 55000, "Development")
            );


    @GetMapping
    public List<Employee> getEmployees(){
        System.out.println("inside first getEmployee");
        return employees;
    }

    @GetMapping("/emp")
    public List<Employee> getEmployees1(){
        System.out.println("inside second getEmployee");
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id){ //104
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

    // api/employees
    @PostMapping
    public String addEmployee(@RequestBody Employee employee){
        System.out.println("input request : "+employee.toString());
        return "request coming to addEmployee";
    }

    @PostMapping("/emp")
    public List<Employee> addEmployee1(@RequestBody Employee employee){
        System.out.println("input request : "+employee.toString());
        List<Employee> emp = new ArrayList<>();
        emp.add(employee);
        return emp;
    }

    @PostMapping("/get")
    public String addEmployee2(@RequestBody Employee employee){
        System.out.println("input request : "+employee.toString());
        return "request coming to addEmployee2";
    }
}
