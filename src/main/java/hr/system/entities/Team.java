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
    private Department department;
    @OneToMany
    private List<Employee> members;
    @OneToOne
    private TeamLead lead;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public Team(String name, Department department, List<Employee> members, TeamLead lead) {
        this.name = name;
        this.department = department;
        this.members = members;
        this.lead = lead;
    }

    public TeamLead getLead() {
        return lead;
    }

    public void setLead(TeamLead lead) {
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
