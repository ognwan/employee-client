package com.fdmgroup.employeeUI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.employeeUI.clients.EmployeesClient;
import com.fdmgroup.employeeUI.model.Employee;

@Service
public class EmployeeService {

    EmployeesClient employeeClient;

    public EmployeeService(EmployeesClient employeeClient) {
        super();
        this.employeeClient = employeeClient;
    }

    public List<Employee> getAllEmployees() {
        return employeeClient.retrieveEmployees();
    }

    public Employee addEmployee(Employee employee) {

        return employeeClient.createEmployee(employee);
    }

    public Employee getEmployee(long id) {

        return employeeClient.retrieveEmployeeById(id);
    }

    public void deleteEmployee(long id) {
        System.out.println("------deleted------");
        employeeClient.deleteEmployee(id);
    }

}
