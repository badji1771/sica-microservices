package com.formation.banque.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.formation.banque.dto.BanqueDto;
import com.formation.banque.dto.BanqueVo;
import com.formation.banque.entity.BanqueEntity;

@Mapper(componentModel = "spring")
public interface BanqueMapper {

	BanqueDto entityToDto(BanqueEntity in);

	@Mapping(source = "in.pays.codeIso", target = "paysCodeIso")
	BanqueVo entityToVo(BanqueEntity in);

	List<BanqueDto> entitiesToDtos(Page<BanqueEntity> in);

	List<BanqueVo> entitiesToVos(Page<BanqueEntity> in);

}
