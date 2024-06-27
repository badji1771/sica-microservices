package org.formation.mappers;

import org.formation.entities.OperationRejet;
import org.formation.service.dto.operationrejet.OperationRejetResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OperationRejetMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OperationRejetResponse operationRejetToOperationRejetResponse(OperationRejet operationRejet);


}
