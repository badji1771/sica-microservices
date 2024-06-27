package org.formation.mappers;

import java.util.List;

import org.formation.entities.Operation;
import org.formation.service.dto.operation.OperationDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface OperationMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "codeTypeOperation", source = "typeOperation.code")
    @Mapping(target = "journeeId", source = "journeeCompensation.id")
    OperationDto  OperationToOperationDto(Operation operation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    Operation OperationDtoToOperation(OperationDto operationDto );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<OperationDto>  OperationToOperationDto( List<Operation> operations);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<Operation> OperationDtoToOperation(List<OperationDto> operationDtos);


}
