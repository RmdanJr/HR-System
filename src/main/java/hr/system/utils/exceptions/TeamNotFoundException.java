package hr.system.utils.exceptions;

import java.util.UUID;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(UUID id) {
        super("Could not find team with id: " + id);
    }
}
