package com.fdmgroup.employeeUI.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.employeeUI.exceptions.EmployeeNotFoundException;
import com.fdmgroup.employeeUI.model.Employee;

import reactor.core.publisher.Mono;

@Service
public class EmployeeWebClient implements EmployeesClient {

    private WebClient webClient;

    @Autowired
    public EmployeeWebClient(WebClient webClient) {
        super();
        this.webClient = webClient;
    }

    @Override
    public List<Employee> retrieveEmployees() {
        return webClient.get()
                .uri(builder -> builder.path("/api/v1/employees").build())
                .retrieve()
                .bodyToFlux(Employee.class)
                .collectList()
                .block();
    }

    @Override
    public Employee retrieveEmployeeById(long id) {
        return webClient.get()
                .uri(builder -> builder.path("/api/v1/employees/{id}").build(id))
                .retrieve()
                .onStatus(status -> status.value() == HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        response -> Mono.error(new EmployeeNotFoundException("no employee found with id" + id)))
                .bodyToMono(Employee.class)
                .block();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return webClient.post()
                .uri(builder -> builder.path("/api/v1/employees")
                        .build())
                .bodyValue(employee)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
    }

    @Override
    public void updateEmployee(Employee employee) {
        webClient.put()
                .uri(builder -> builder.path("/api/v1/employees")
                        .build())
                .bodyValue(employee)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    @Override
    public void deleteEmployee(long id) {
        System.out.println(id);
        webClient.delete()
                .uri(builder -> builder.path("/api/v1/employees/{id}")
                        .build(id))
                .retrieve()
                .toBodilessEntity()
                .block();
    }

}
