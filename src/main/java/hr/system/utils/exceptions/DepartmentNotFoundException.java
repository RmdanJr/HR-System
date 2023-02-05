package hr.system.utils.exceptions;

import java.util.UUID;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(UUID id) {
        super("Could not find employee with id: " + id);
    }
}