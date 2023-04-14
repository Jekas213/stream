package com.example.stream.service;

import com.example.stream.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {
    final EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Employee getMinSalaryByDep(int dep) {
        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == dep)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow();
    }

    public Employee getMaxSalaryByDep(int dep) {
        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == dep)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow();
    }

    public List<Employee> employeesByDepartment(int dep) {
        return employeeService.getEmployees().values().stream()
                .filter(e -> e.getDepartment() == dep)
                .collect(Collectors.toList());
    }

    public Set<Map.Entry<Integer, List<Employee>>> employeesByDepartments (){
        return employeeService.getEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet();

    }

}
