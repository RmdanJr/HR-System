package hr.system.repositories;

import hr.system.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update employee set employee.managed_department_id = :managedDepartmentId where employee.id = :id", nativeQuery = true)
    void updateManagedDepartment(UUID id, UUID managedDepartmentId);

    @Transactional
    @Modifying
    @Query(value = "update employee set employee.managed_team_id = :managedTeamId where employee.id = :id", nativeQuery = true)
    void updateManagedTeam(UUID id, UUID managedTeamId);

    @Transactional
    @Modifying
    @Query(value = "update employee set employee.department_id = :departmentId where employee.team_id = :teamId", nativeQuery = true)
    void changeDepartmentRegardingToTeam(UUID departmentId, UUID teamId);

    @Transactional
    @Modifying
    @Query(value = "update employee set employee.department_id = :departmentId where employee.id = :id", nativeQuery = true)
    void updateDepartment(UUID id, UUID departmentId);

    @Transactional
    @Modifying
    @Query(value = "update employee set employee.team_id = :teamId where employee.id = :id", nativeQuery = true)
    void updateTeam(UUID id, UUID teamId);

    @Transactional
    @Modifying
    @Query(value = "update employee set employee.manager_id = :managerId where employee.id = :id", nativeQuery = true)
    void updateManager(UUID id, UUID managerId);
}