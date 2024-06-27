package org.formation.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.formation.dto.FichierDetailsReglementDTO;
import org.formation.dto.FichierEnteteReglementDTO;
import org.formation.dto.ReglementDTO;
import org.formation.model.FichierDetailsReglementEntity;
import org.formation.model.FichierEnteteReglementEntity;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class EntityDtoUtilTest {

    @Test
    public void testFichierEnteteToDto() {
        FichierEnteteReglementEntity entity = new FichierEnteteReglementEntity();
        entity.setId(1L);
        entity.setNomFichier("TestFile");
        entity.setDateCreation(LocalDate.now());
        entity.setIdJourneeCompense(100L);

        FichierEnteteReglementDTO dto = EntityDtoUtil.fichierEnteteToDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNomFichier(), dto.getNomFichier());
        assertEquals(entity.getDateCreation(), dto.getDateCreation());
        assertEquals(entity.getIdJourneeCompense(), dto.getIdJourneeCompense());
    }

    @Test
    public void testFichierEnteteToEntity() {
        FichierEnteteReglementDTO dto = new FichierEnteteReglementDTO();
        dto.setId(1L);
        dto.setNomFichier("TestFile");
        dto.setDateCreation(LocalDate.now());
        dto.setIdJourneeCompense(100L);

        FichierEnteteReglementEntity entity = EntityDtoUtil.fichierEnteteToEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNomFichier(), entity.getNomFichier());
        assertEquals(dto.getDateCreation(), entity.getDateCreation());
        assertEquals(dto.getIdJourneeCompense(), entity.getIdJourneeCompense());
    }

    @Test
    public void testFichierDetailsToDto() {
        FichierDetailsReglementEntity entity = new FichierDetailsReglementEntity();
        entity.setId(1L);
        entity.setJourneeCompensationId(200L);
        entity.setIdSoldeCompense(300L);
        entity.setDateOperation(new Date());
        entity.setBanqueId(400L);
        entity.setSolde(new BigDecimal("500.00"));
        entity.setEtatSoldeCompensation("Valid");
        entity.setFichierEnteteReglement(600L);

        FichierDetailsReglementDTO dto = EntityDtoUtil.fichierDetailsToDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getJourneeCompensationId(), dto.getJourneeCompensationId());
        assertEquals(entity.getIdSoldeCompense(), dto.getIdSoldeCompense());
        assertEquals(entity.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dto.getDateOperation());
        assertEquals(entity.getBanqueId(), dto.getBanqueId());
        assertEquals(entity.getSolde(), dto.getSolde());
        assertEquals(entity.getEtatSoldeCompensation(), dto.getEtatSoldeCompensation());
        assertEquals(entity.getFichierEnteteReglement(), dto.getFichierEnteteReglement());
    }

    @Test
    public void testFichierDetailsToEntity() {
        FichierDetailsReglementDTO dto = new FichierDetailsReglementDTO();
        dto.setId(1L);
        dto.setJourneeCompensationId(200L);
        dto.setIdSoldeCompense(300L);
        dto.setDateOperation(LocalDate.now());
        dto.setBanqueId(400L);
        dto.setSolde(new BigDecimal("500.00"));
        dto.setEtatSoldeCompensation("Valid");
        dto.setFichierEnteteReglement(600L);

        FichierDetailsReglementEntity entity = EntityDtoUtil.fichierDetailsToEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getJourneeCompensationId(), entity.getJourneeCompensationId());
        assertEquals(dto.getIdSoldeCompense(), entity.getIdSoldeCompense());
        assertEquals(dto.getDateOperation(), entity.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        assertEquals(dto.getBanqueId(), entity.getBanqueId());
        assertEquals(dto.getSolde(), entity.getSolde());
        assertEquals(dto.getEtatSoldeCompensation(), entity.getEtatSoldeCompensation());
        assertEquals(dto.getFichierEnteteReglement(), entity.getFichierEnteteReglement());
    }

    @Test
    public void testEnteteDetailsDTO() {
        FichierEnteteReglementDTO fichierEntete = new FichierEnteteReglementDTO();
        fichierEntete.setId(1L);
        fichierEntete.setNomFichier("TestFile");
        fichierEntete.setDateCreation(LocalDate.now());
        fichierEntete.setIdJourneeCompense(100L);

        FichierDetailsReglementEntity detail1 = new FichierDetailsReglementEntity();
        detail1.setId(1L);
        detail1.setJourneeCompensationId(200L);
        detail1.setIdSoldeCompense(300L);
        detail1.setDateOperation(new Date());
        detail1.setBanqueId(400L);
        detail1.setSolde(new BigDecimal("500.00"));
        detail1.setEtatSoldeCompensation("Valid");
        detail1.setFichierEnteteReglement(600L);

        FichierDetailsReglementEntity detail2 = new FichierDetailsReglementEntity();
        detail2.setId(2L);
        detail2.setJourneeCompensationId(201L);
        detail2.setIdSoldeCompense(301L);
        detail2.setDateOperation(new Date());
        detail2.setBanqueId(401L);
        detail2.setSolde(new BigDecimal("501.00"));
        detail2.setEtatSoldeCompensation("Valid");
        detail2.setFichierEnteteReglement(601L);

        List<FichierDetailsReglementEntity> details = Arrays.asList(detail1, detail2);

        ReglementDTO reglementDTO = EntityDtoUtil.enteteDetailsDTO(fichierEntete, details);

        assertNotNull(reglementDTO);
        assertEquals(fichierEntete.getNomFichier(), reglementDTO.getNomFichier());
        assertEquals(fichierEntete.getDateCreation(), reglementDTO.getDateCreation());
        assertEquals(2, reglementDTO.getFichierDetailsReglementDTOs().size());
        assertEquals(detail1.getJourneeCompensationId(), reglementDTO.getFichierDetailsReglementDTOs().get(0).getJourneeCompensationId());
        assertEquals(detail1.getJourneeCompensationId(), reglementDTO.getFichierDetailsReglementDTOs().get(0).getJourneeCompensationId());
        assertEquals(detail1.getBanqueId(), reglementDTO.getFichierDetailsReglementDTOs().get(0).getBanqueId());
        assertEquals(detail1.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                , reglementDTO.getFichierDetailsReglementDTOs().get(0).getDateOperation());
        assertEquals(detail1.getSolde(), reglementDTO.getFichierDetailsReglementDTOs().get(0).getSolde());
        assertEquals(detail1.getIdSoldeCompense(), reglementDTO.getFichierDetailsReglementDTOs().get(0).getIdSoldeCompense());
        assertEquals(detail1.getEtatSoldeCompensation(), reglementDTO.getFichierDetailsReglementDTOs().get(0).getEtatSoldeCompensation());
        assertEquals(detail1.getFichierEnteteReglement(), reglementDTO.getFichierDetailsReglementDTOs().get(0).getFichierEnteteReglement());
        assertEquals(detail2.getJourneeCompensationId(), reglementDTO.getFichierDetailsReglementDTOs().get(1).getJourneeCompensationId());
        assertEquals(detail2.getBanqueId(), reglementDTO.getFichierDetailsReglementDTOs().get(1).getBanqueId());
        assertEquals(detail2.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                , reglementDTO.getFichierDetailsReglementDTOs().get(1).getDateOperation());
        assertEquals(detail2.getSolde(), reglementDTO.getFichierDetailsReglementDTOs().get(1).getSolde());
        assertEquals(detail2.getIdSoldeCompense(), reglementDTO.getFichierDetailsReglementDTOs().get(1).getIdSoldeCompense());
        assertEquals(detail2.getEtatSoldeCompensation(), reglementDTO.getFichierDetailsReglementDTOs().get(1).getEtatSoldeCompensation());
        assertEquals(detail2.getFichierEnteteReglement(), reglementDTO.getFichierDetailsReglementDTOs().get(1).getFichierEnteteReglement());
    }

    @Test
    public void testEntityToDto() {
        FichierDetailsReglementEntity detail1 = new FichierDetailsReglementEntity();
        detail1.setId(1L);
        detail1.setJourneeCompensationId(200L);
        detail1.setIdSoldeCompense(300L);
        detail1.setDateOperation(new Date());
        detail1.setBanqueId(400L);
        detail1.setSolde(new BigDecimal("500.00"));
        detail1.setEtatSoldeCompensation("Valid");
        detail1.setFichierEnteteReglement(600L);

        FichierDetailsReglementEntity detail2 = new FichierDetailsReglementEntity();
        detail2.setId(2L);
        detail2.setJourneeCompensationId(201L);
        detail2.setIdSoldeCompense(301L);
        detail2.setDateOperation(new Date());
        detail2.setBanqueId(401L);
        detail2.setSolde(new BigDecimal("501.00"));
        detail2.setEtatSoldeCompensation("Valid");
        detail2.setFichierEnteteReglement(601L);

        List<FichierDetailsReglementEntity> details = Arrays.asList(detail1, detail2);

        List<FichierDetailsReglementDTO> dtos = EntityDtoUtil.entityToDto(details);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(detail1.getJourneeCompensationId(), dtos.get(0).getJourneeCompensationId());
        assertEquals(detail1.getJourneeCompensationId(), dtos.get(0).getJourneeCompensationId());
        assertEquals(detail1.getBanqueId(), dtos.get(0).getBanqueId());
        assertEquals(detail1.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                , dtos.get(0).getDateOperation());
        assertEquals(detail1.getSolde(), dtos.get(0).getSolde());
        assertEquals(detail1.getIdSoldeCompense(), dtos.get(0).getIdSoldeCompense());
        assertEquals(detail1.getEtatSoldeCompensation(), dtos.get(0).getEtatSoldeCompensation());
        assertEquals(detail1.getFichierEnteteReglement(), dtos.get(0).getFichierEnteteReglement());
        assertEquals(detail2.getJourneeCompensationId(), dtos.get(1).getJourneeCompensationId());
        assertEquals(detail2.getBanqueId(), dtos.get(1).getBanqueId());
        assertEquals(detail2.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                , dtos.get(1).getDateOperation());
        assertEquals(detail2.getSolde(), dtos.get(1).getSolde());
        assertEquals(detail2.getIdSoldeCompense(), dtos.get(1).getIdSoldeCompense());
        assertEquals(detail2.getEtatSoldeCompensation(), dtos.get(1).getEtatSoldeCompensation());
        assertEquals(detail2.getFichierEnteteReglement(), dtos.get(1).getFichierEnteteReglement());
    }

    @Test
    public void testFluxEntityToFluxDto() {
        FichierDetailsReglementEntity detail1 = new FichierDetailsReglementEntity();
        detail1.setId(1L);
        detail1.setJourneeCompensationId(200L);
        detail1.setIdSoldeCompense(300L);
        detail1.setDateOperation(new Date());
        detail1.setBanqueId(400L);
        detail1.setSolde(new BigDecimal("500.00"));
        detail1.setEtatSoldeCompensation("Valid");
        detail1.setFichierEnteteReglement(600L);

        FichierDetailsReglementEntity detail2 = new FichierDetailsReglementEntity();
        detail2.setId(2L);
        detail2.setJourneeCompensationId(201L);
        detail2.setIdSoldeCompense(301L);
        detail2.setDateOperation(new Date());
        detail2.setBanqueId(401L);
        detail2.setSolde(new BigDecimal("501.00"));
        detail2.setEtatSoldeCompensation("Valid");
        detail2.setFichierEnteteReglement(601L);

        Flux<FichierDetailsReglementEntity> detailsFlux = Flux.just(detail1, detail2);
        List<FichierDetailsReglementDTO> dtos = EntityDtoUtil.fluxEntityToFluxDto(detailsFlux);

        assertNotNull(dtos);
        assertEquals(2, dtos.size());
        assertEquals(detail1.getJourneeCompensationId(), dtos.get(0).getJourneeCompensationId());
        assertEquals(detail1.getJourneeCompensationId(), dtos.get(0).getJourneeCompensationId());
        assertEquals(detail1.getBanqueId(), dtos.get(0).getBanqueId());
        assertEquals(detail1.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                , dtos.get(0).getDateOperation());
        assertEquals(detail1.getSolde(), dtos.get(0).getSolde());
        assertEquals(detail1.getIdSoldeCompense(), dtos.get(0).getIdSoldeCompense());
        assertEquals(detail1.getEtatSoldeCompensation(), dtos.get(0).getEtatSoldeCompensation());
        assertEquals(detail1.getFichierEnteteReglement(), dtos.get(0).getFichierEnteteReglement());
        assertEquals(detail2.getJourneeCompensationId(), dtos.get(1).getJourneeCompensationId());
        assertEquals(detail2.getBanqueId(), dtos.get(1).getBanqueId());
        assertEquals(detail2.getDateOperation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                , dtos.get(1).getDateOperation());
        assertEquals(detail2.getSolde(), dtos.get(1).getSolde());
        assertEquals(detail2.getIdSoldeCompense(), dtos.get(1).getIdSoldeCompense());
        assertEquals(detail2.getEtatSoldeCompensation(), dtos.get(1).getEtatSoldeCompensation());
        assertEquals(detail2.getFichierEnteteReglement(), dtos.get(1).getFichierEnteteReglement());
    }


}
