package com.empmgmt.employee.controller;

import com.empmgmt.employee.entity.Employee;
import com.empmgmt.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

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
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        System.out.println("input request : " + employee.toString());
        //employees.add(employee);
        Employee savedEmployee = employeeServices.saveEmployee(employee);
        return new ResponseEntity<>("Employee inserted successfully with id : " + savedEmployee.getId(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
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
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {//2,
        String updateResponse = employeeServices.updateEmployee(id, employee);
        if (updateResponse.contains("updated")) { // Assuming your service gives meaningful feedback
            return new ResponseEntity<>(updateResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(updateResponse, HttpStatus.NOT_FOUND);
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
