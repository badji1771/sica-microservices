package org.formation.mappers;

import org.formation.entities.TypeRejet;
import org.formation.service.dto.typerejet.TypeRejetRequest;
import org.formation.service.dto.typerejet.TypeRejetResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface TypeRejetMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TypeRejetResponse typeRejetToTypeRejetResponse(TypeRejet rejet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TypeRejet typeRejetRequestToTypeRejet(TypeRejetRequest request);
}
