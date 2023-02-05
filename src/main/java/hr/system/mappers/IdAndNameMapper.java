package hr.system.mappers;

import hr.system.dtos.IdAndName;
import hr.system.utils.interfaces.hasIdAndName;
import org.springframework.stereotype.Component;

@Component
public class IdAndNameMapper {
    IdAndName convertToDto(hasIdAndName entity) {
        IdAndName idAndName = new IdAndName();
        if (entity == null) return idAndName;
        idAndName.setId(entity.getId());
        idAndName.setName(entity.getName());
        return idAndName;
    }
}
