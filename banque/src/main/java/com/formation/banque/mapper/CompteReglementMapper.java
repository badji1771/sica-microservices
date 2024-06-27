package com.formation.banque.mapper;

import org.mapstruct.Mapper;

import com.formation.banque.dto.CompteReglementDto;
import com.formation.banque.entity.CompteReglementEntity;

@Mapper(componentModel = "spring", uses = BanqueMapper.class)
public interface CompteReglementMapper {

	CompteReglementDto entityToDto(CompteReglementEntity in);

}
