package hr.system.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Department {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    private DepartmentManager manager;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Team> teams;

    public Department() {}

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public Department(String name, DepartmentManager manager, List<Employee> employees, List<Team> teams) {
        this.name = name;
        this.manager = manager;
        this.employees = employees;
        this.teams = teams;
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

    public DepartmentManager getManager() {
        return manager;
    }

    public void setManager(DepartmentManager manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
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
                ", manager=" + manager +
                ", employees=" + employees +
                ", teams=" + teams +
                '}';
    }
}
