package hr.system.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "team")
    private List<Employee> members;
    @OneToOne
    @JoinColumn(name = "lead_id")
    private DepartmentManager lead;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public Team(String name, Department department, List<Employee> members, DepartmentManager lead) {
        this.name = name;
        this.department = department;
        this.members = members;
        this.lead = lead;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getMembers() {
        return members;
    }

    public void setMembers(List<Employee> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        return getId().equals(team.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", members=" + members +
                ", lead=" + lead +
                '}';
    }
}
