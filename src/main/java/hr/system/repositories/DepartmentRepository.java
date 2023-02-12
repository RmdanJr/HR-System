package hr.system.repositories;

import hr.system.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    @Transactional
    @Modifying
    @Query(value = "update department set department.name = :name where department.id = :id", nativeQuery = true)
    void updateName(UUID id, String name);
}
