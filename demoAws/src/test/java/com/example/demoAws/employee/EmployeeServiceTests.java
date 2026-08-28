package com.example.demoAws.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTests {

    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void createAndFetchEmployee() {
        Employee employee = new Employee(null, "John", "Doe", "john@example.com", "Engineering", "Developer", 90000.0);

        Employee createdEmployee = employeeService.createEmployee(employee);

        assertEquals(1L, createdEmployee.getId());
        assertEquals("John", employeeService.getEmployeeById(1L).getFirstName());
    }

    @Test
    void deleteEmployeeShouldMakeLookupFail() {
        Employee employee = new Employee(null, "Jane", "Smith", "jane@example.com", "HR", "Manager", 85000.0);
        Employee createdEmployee = employeeService.createEmployee(employee);

        employeeService.deleteEmployee(createdEmployee.getId());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(createdEmployee.getId()));
    }
}
