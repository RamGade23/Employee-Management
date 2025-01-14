package com.empmgmt.employee.service;

import com.empmgmt.employee.entity.Employee;
import com.empmgmt.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll(); //SELECT * FROM employee;
    }

    public Employee getEmployeeById(Long id) {//id = 7
         Optional<Employee> employee = employeeRepository.findById(id); //select * from employee where id = 8;
        return employee.orElse(new Employee());
    }
}
