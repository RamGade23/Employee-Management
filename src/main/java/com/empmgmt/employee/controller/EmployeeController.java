package com.empmgmt.employee.controller;

import com.empmgmt.employee.entity.Employee;
import com.empmgmt.employee.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private String abc;

    @PostConstruct
    public void init() {
        // This method will be executed after the bean's dependencies are injected
        abc = "Vishal";
        System.out.println("EmployeeController has been initialized");
    }

    //Field injection
//    @Autowired
    private EmployeeService employeeServices;

    //Constructor injection
    public EmployeeController(EmployeeService employeeServices) {
        System.out.println("EmployeeController : constructor injection");
        this.employeeServices = employeeServices;
    }

    // api/employees
    @PostMapping
    public ResponseEntity<Map<String, String>> addEmployee(@RequestBody Employee employee) {
        System.out.println("input request : " + employee.toString());
        //employees.add(employee);
        Employee savedEmployee = employeeServices.saveEmployee(employee);
        String resp = "Employee inserted successfully with id : " + savedEmployee.getId();
        Map<String, String> response = new HashMap<>();
        response.put("message", resp);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        System.out.println("abc : " + abc);
        System.out.println("inside getEmployee method");
        List<Employee> employeeList = employeeServices.getEmployees();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @GetMapping("/{id}") //api/employees/8
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {//id =20
        Employee employee = employeeServices.getEmployeeById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {//2,
        String updateResponse = employeeServices.updateEmployee(id, employee);
        Map<String, String> response = new HashMap<>();
        response.put("message", updateResponse);
        if (updateResponse.contains("updated")) { // Assuming your service gives meaningful feedback
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {//2
        String response = employeeServices.deleteEmployee(id);
        if (response.contains("successfully")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
