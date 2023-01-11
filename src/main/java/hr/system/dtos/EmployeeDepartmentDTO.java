package hr.system.dtos;

public class EmployeeDepartmentDTO {
    private String name;
    private String departmentManager;

    public EmployeeDepartmentDTO() {
        this.name = "";
        this.departmentManager = "";
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
