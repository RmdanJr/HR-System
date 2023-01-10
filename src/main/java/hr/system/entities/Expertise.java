package hr.system.entities;

import hr.system.utils.ExpertiseLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Expertise {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;
    private String name;
    private ExpertiseLevel level;

    public Expertise() {
    }

    public Expertise(String name, ExpertiseLevel level) {
        this.name = name;
        this.level = level;
    }

    public UUID getId() {
        return id;
    }

    public void setDd(UUID dd) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpertiseLevel getLevel() {
        return level;
    }

    public void setLevel(ExpertiseLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expertise expertise)) return false;
        return Objects.equals(getName(), expertise.getName()) && getLevel() == expertise.getLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLevel());
    }

    @Override
    public String toString() {
        return "Expertise{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
