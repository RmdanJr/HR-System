package hr.system.repositories;

import hr.system.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    @Transactional
    @Modifying
    @Query(value = "update team set team.name = :name where team.id = :id", nativeQuery = true)
    void updateName(UUID id, String name);

    @Transactional
    @Modifying
    @Query(value = "update team set team.department_id = :departmentId where team.id = :id", nativeQuery = true)
    void updateDepartment(UUID id, UUID departmentId);
}
