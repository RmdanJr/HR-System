package hr.system.dtos;

import java.util.UUID;

public class EmployeeDepartmentDTO {
    private UUID id;
    private String name;
    private String departmentManager;

    public EmployeeDepartmentDTO() {
        this.name = "";
        this.departmentManager = "";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }
}
