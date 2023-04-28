package com.example.stream.service;

import org.springframework.stereotype.Service;
import com.example.stream.domain.Employee;
import com.example.stream.exceptions.EmployeeAlreadyAddedException;
import com.example.stream.exceptions.EmployeeNotFoundException;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Map<String, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    public Employee add(String employeeName, String employeeSureName, int employeeSalary, int employeeDepartment) {
        Employee employee = new Employee(employeeName, employeeSureName, employeeSalary, employeeDepartment);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee remove(String employeeName, String employeeSureName, int employeeSalary, int employeeDepartment) {
        Employee employee = new Employee(employeeName, employeeSureName, employeeSalary, employeeDepartment);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(employee.getFullName());

    }

    public Employee find(String employeeName, String employeeSureName, int employeeSalary, int employeeDepartment) {
        Employee employee = new Employee(employeeName, employeeSureName, employeeSalary, employeeDepartment);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employee.getFullName());
    }

    public Employee changeEmployeeSalary(String employeeName, String employeeSureName, int newSalary) {
        final String fullName = employeeName + " " + employeeSureName;
        if (!employees.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        employees.get(fullName).setSalary(newSalary);

        return employees.get(fullName);
    }

    public Employee changeEmployeeDepart(String employeeName, String employeeSureName, int newDepart) {
        final String fullName = employeeName + " " + employeeSureName;
        if (!employees.containsKey(fullName)) {
            throw new EmployeeNotFoundException();
        }
        employees.get(fullName).setDepartment(newDepart);

        return employees.get(fullName);
    }

    public List<Employee> employeesByDepartment(int numberDepartment) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getDepartment() == numberDepartment) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }


    public double getAllSalary() {
        int allSalary = 0;
        for (Employee employee : employees.values()) {
            allSalary += employee.getSalary();
        }
        return allSalary;
    }

    public Employee getEmployeeMinSalary() {
        List<Employee> employeeList = new ArrayList<>(employees.values());
        return Collections.min(employeeList);

    }

    public Employee getEmployeeMaxSalary() {
        List<Employee> employeeList = new ArrayList<>(employees.values());
        return Collections.max(employeeList);

    }


    public double getAverageSalary() {
        double average = getAllSalary() / employees.size();
        return Math.round(average * 100) / 100.0d;
    }


    public Collection<Employee> showAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
