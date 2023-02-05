package hr.system.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamDTO {
    private UUID id;
    private String name;
    private IdAndName department;
    private IdAndName lead;
    private List<IdAndName> members = new ArrayList<>();

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

    public IdAndName getDepartment() {
        return department;
    }

    public void setDepartment(IdAndName department) {
        this.department = department;
    }

    public IdAndName getLead() {
        return lead;
    }

    public void setLead(IdAndName lead) {
        this.lead = lead;
    }

    public List<IdAndName> getMembers() {
        return members;
    }

    public void setMembers(List<IdAndName> members) {
        this.members = members;
    }
}
