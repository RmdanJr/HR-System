package hr.system.dtos;

import java.util.UUID;

public class IdAndName {

    private UUID id;
    private String name;

    public IdAndName() {
        this.name = "";
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
}
