package hr.system.repositories;

import hr.system.entities.DepartmentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, UUID> {
}
