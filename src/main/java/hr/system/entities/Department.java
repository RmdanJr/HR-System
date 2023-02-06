package hr.system.entities;

import hr.system.utils.interfaces.hasIdAndName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Department implements hasIdAndName {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;
    private String name;
    @OneToOne(mappedBy = "managedDepartment")
    private Employee manager;
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<Team> teams = new ArrayList<>();
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, Employee manager, List<Team> teams, List<Employee> employees) {
        this.name = name;
        this.manager = manager;
        this.teams = teams;
        this.employees = employees;
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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                (manager != null ? ", manager=" + manager.getName() : "") +
                ", teams=" + teams.stream().map(Team::getName).toList() +
                ", employees=" + employees.stream().map(Employee::getName).toList() +
                '}';
    }
}
