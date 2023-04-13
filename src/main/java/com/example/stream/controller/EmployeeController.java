package com.example.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.stream.domain.Employee;
import com.example.stream.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/all")
    public List<Employee> findEmployee() {
        return employeeService.showAll();
    }

}
