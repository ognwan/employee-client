package com.fdmgroup.employeeUI.clients;

import java.util.List;

import com.fdmgroup.employeeUI.model.Employee;

public interface EmployeesClient {
    List<Employee> retrieveEmployees();

    Employee retrieveEmployeeById(long id);

    Employee createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(long id);
}
