package org.formation.mappers;

import org.formation.entities.JourneeCompensation;
import org.formation.service.dto.journeecompensation.JourCompensationRequest;
import org.formation.service.dto.journeecompensation.JourCompensationResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface JourneeCompensationMapper {
	
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JourneeCompensation jourCompensationRequestToJourneeCompensation(JourCompensationRequest request);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JourCompensationResponse journeeCompensationToJourCompensationResponse(JourneeCompensation journeeCompensation);
}
