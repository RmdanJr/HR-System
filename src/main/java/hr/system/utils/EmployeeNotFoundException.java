package hr.system.utils;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(UUID id) {
        super("Could not find employee with id: " + id);
    }
}