package com.empmgmt.employee.service;

import com.empmgmt.employee.entity.Employee;
import com.empmgmt.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")
public class EmployeeService {

    //field injection
    //@Autowired
    private EmployeeRepository employeeRepository;

    //Constructor injection
//    public EmployeeService(EmployeeRepository employeeRepository) {
//        System.out.println("EmployeeService : constructor injection");
//        this.employeeRepository = employeeRepository;
//    }

    //setter injection
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        System.out.println("EmployeeService : setter injection");
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll(); //SELECT * FROM employee;
    }

    public Employee getEmployeeById(Long id) {//id = 20
        Optional<Employee> employee = employeeRepository.findById(id); //select * from employee where id = 8;
        return employee.orElse(null);
    }

    public String updateEmployee(Long id, Employee employee) {//7, Diksha
        Employee empFromDb = getEmployeeById(id); // actual object or null
        if (empFromDb == null) {
            return "Requested employee is not present in the database. please use different id";
        } else {
            empFromDb.setDepartment(employee.getDepartment());
            empFromDb.setSalary(employee.getSalary());
            empFromDb.setName(employee.getName());
            saveEmployee(empFromDb);
            return "Employee updated successfully!";
        }
    }

    public String deleteEmployee(Long id) {
        Employee empFromDb = getEmployeeById(id);
        if (empFromDb != null) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully!";
        }
        return "Requested employee is not present in the database";
    }
}
