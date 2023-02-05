package hr.system.mappers;

import hr.system.dtos.DepartmentDTO;
import hr.system.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    @Autowired
    private final IdAndNameMapper idAndNameMapper;

    public DepartmentMapper(IdAndNameMapper idAndNameMapper) {
        this.idAndNameMapper = idAndNameMapper;
    }

    public DepartmentDTO convertToDto(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        if (department == null) return departmentDTO;
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setManager(idAndNameMapper.convertToDto(department.getManager()));
        departmentDTO.setTeams(department.getTeams().stream().map(idAndNameMapper::convertToDto).toList());
        departmentDTO.setEmployees(department.getEmployees().stream().map(idAndNameMapper::convertToDto).toList());
        return departmentDTO;
    }
}
