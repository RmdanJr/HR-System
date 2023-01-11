package hr.system.repositories;

import hr.system.entities.TeamLead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamLeadRepository extends JpaRepository<TeamLead, UUID> {
}
