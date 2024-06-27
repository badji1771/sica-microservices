package org.formation.util;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.formation.dto.FichierDetailsReglementDTO;
import org.formation.dto.FichierEnteteReglementDTO;
import org.formation.dto.ReglementDTO;
import org.formation.model.FichierDetailsReglementEntity;
import org.formation.model.FichierEnteteReglementEntity;
import org.springframework.beans.BeanUtils;

import reactor.core.publisher.Flux;

public class EntityDtoUtil {

    private EntityDtoUtil() {

    }

    public static FichierEnteteReglementDTO fichierEnteteToDto(FichierEnteteReglementEntity entity) {
        FichierEnteteReglementDTO dto = new FichierEnteteReglementDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static FichierEnteteReglementEntity fichierEnteteToEntity(FichierEnteteReglementDTO Dto) {
        FichierEnteteReglementEntity entity = new FichierEnteteReglementEntity();
        BeanUtils.copyProperties(Dto, entity);
        return entity;
    }

    public static FichierDetailsReglementDTO fichierDetailsToDto(FichierDetailsReglementEntity entity) {
        FichierDetailsReglementDTO dto = new FichierDetailsReglementDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setDateOperation(entity.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return dto;
    }

    public static FichierDetailsReglementEntity fichierDetailsToEntity(FichierDetailsReglementDTO Dto) {
        FichierDetailsReglementEntity entity = new FichierDetailsReglementEntity();
        BeanUtils.copyProperties(Dto, entity);
        entity.setDateOperation(DateUtil.convertToDate(Dto.getDateOperation()));
        return entity;
    }

    public static ReglementDTO enteteDetailsDTO(FichierEnteteReglementDTO fichierEntete, List<FichierDetailsReglementEntity> details) {

        ReglementDTO reglementDTO = new ReglementDTO();
        reglementDTO.setNomFichier(fichierEntete.getNomFichier());
        reglementDTO.setDateCreation(fichierEntete.getDateCreation());
        reglementDTO.setFichierDetailsReglementDTOs(entityToDto(details));

        return reglementDTO;

    }

    public static List<FichierDetailsReglementDTO> entityToDto(List<FichierDetailsReglementEntity> details) {

        return details.stream().map(EntityDtoUtil::fichierDetailsToDto).collect(Collectors.toList());
    }

    public static List<FichierDetailsReglementDTO> fluxEntityToFluxDto(Flux<FichierDetailsReglementEntity> details) {

        List<FichierDetailsReglementDTO> fichierDTOs = new ArrayList<>();
        details.subscribe(fichier -> fichierDTOs.add(fichierDetailsToDto(fichier)));

        return fichierDTOs;
    }
}
