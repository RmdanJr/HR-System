package hr.system.mappers;

import hr.system.dtos.EmployeeDepartmentDTO;
import hr.system.entities.Department;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDepartmentMapper {
    public EmployeeDepartmentDTO convertToDto(Department department) {
        EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
        if (department != null) {
            employeeDepartmentDTO.setName(department.getName());
            if (department.getManager() != null) {
                employeeDepartmentDTO.setDepartmentManager(department.getManager().getName());
            }
        }
        return employeeDepartmentDTO;
    }
}
