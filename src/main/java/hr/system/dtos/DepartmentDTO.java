package hr.system.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentDTO {
    private UUID id;
    private String name;
    private IdAndName manager;
    private List<IdAndName> teams = new ArrayList<>();
    private List<IdAndName> employees = new ArrayList<>();

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

    public IdAndName getManager() {
        return manager;
    }

    public void setManager(IdAndName manager) {
        this.manager = manager;
    }

    public List<IdAndName> getTeams() {
        return teams;
    }

    public void setTeams(List<IdAndName> teams) {
        this.teams = teams;
    }

    public List<IdAndName> getEmployees() {
        return employees;
    }

    public void setEmployees(List<IdAndName> employees) {
        this.employees = employees;
    }
}