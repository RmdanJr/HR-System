package hr.system.utils.interfaces;

import java.util.UUID;

public interface hasIdAndName {
    UUID getId();

    void setId(UUID id);

    String getName();

    void setName(String name);
}
