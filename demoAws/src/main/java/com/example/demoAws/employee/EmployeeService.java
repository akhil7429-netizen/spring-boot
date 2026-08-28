package com.example.demoAws.employee;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final Map<Long, Employee> employees = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeById(Long id) {
        Employee employee = employees.get(id);
        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        Long id = idGenerator.incrementAndGet();
        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        if (!employees.containsKey(id)) {
            throw new EmployeeNotFoundException(id);
        }

        employee.setId(id);
        employees.put(id, employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        if (employees.remove(id) == null) {
            throw new EmployeeNotFoundException(id);
        }
    }
}
