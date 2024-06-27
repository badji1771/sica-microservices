package org.formation.service.reglement;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.formation.dto.FichierDetailsReglementDTO;
import org.formation.dto.FichierEnteteReglementDTO;
import org.formation.dto.SoldeCompensationDTO;
import org.formation.model.FichierEnteteReglementEntity;
import org.formation.repository.FichierDetailsReglementRepository;
import org.formation.repository.FichierEnteteReglementRepository;
import org.formation.service.ReglementService;
import org.formation.util.EntityDtoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ReglementServiceTest {

    @Mock
    private FichierEnteteReglementRepository fichierEnteteReglementRepository;

    @Mock
    private FichierDetailsReglementRepository fichierDetailsReglementRepository;

    @InjectMocks
    private ReglementService reglementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void testCreateReglement() {
        SoldeCompensationDTO soldeCompensation = SoldeCompensationDTO.builder()
                .idSoldeCompense(2)
                .dateOperation(LocalDate.now())
                .banqueId(2)
                .solde(new BigDecimal(100000))
                .etatSoldeCompensation("AT")
                .journeeCompensationId(5)
                .build();

        SoldeCompensationDTO[] soldeCompensations = { soldeCompensation };

        FichierEnteteReglementEntity savedEntity = new FichierEnteteReglementEntity();
        savedEntity.setId(1L);
        savedEntity.setDateCreation(LocalDate.now());
        savedEntity.setNomFichier("compensation_" + LocalDate.now());

        when(fichierEnteteReglementRepository.save(any(FichierEnteteReglementEntity.class)))
                .thenReturn(Mono.just(savedEntity));

        when(fichierDetailsReglementRepository.save(any())).thenReturn(Mono.empty());

        Mono<FichierEnteteReglementDTO> result = reglementService.createReglement(soldeCompensations);

        StepVerifier.create(result)
                .assertNext(dto -> {
                    Assertions.assertNotNull(dto);
                    Assertions.assertNotNull(dto.getDateCreation());
                    Assertions.assertNotNull(dto.getNomFichier());
                })
                .verifyComplete();
    }

    @Test
    @Order(2)
    void testGetReglementById() {
        FichierEnteteReglementEntity entity = new FichierEnteteReglementEntity();
        entity.setId(1L);

        when(fichierEnteteReglementRepository.findById(1L))
                .thenReturn(Mono.just(entity));

        Mono<FichierEnteteReglementDTO> result = reglementService.getReglementById(1L);

        StepVerifier.create(result)
                .assertNext(dto -> {
                    Assertions.assertNotNull(dto);
                    Assertions.assertEquals(1L, dto.getId());
                })
                .verifyComplete();
    }

    @Test
    @Order(3)
    void testGetReglementByJourneeCompensation() {
        FichierEnteteReglementEntity entity = new FichierEnteteReglementEntity();
        entity.setId(1L);
        entity.setIdJourneeCompense(5L);

        when(fichierEnteteReglementRepository.findByIdJourneeCompense(5L))
                .thenReturn(Flux.just(entity));

        Flux<FichierEnteteReglementDTO> result = reglementService.getReglementByJourneeCompensation(5L);

        StepVerifier.create(result)
                .assertNext(dto -> {
                    Assertions.assertNotNull(dto);
                    Assertions.assertEquals(5L, dto.getIdJourneeCompense());
                })
                .verifyComplete();
    }

    @Test
    @Order(4)
    void testGetDetailsReglementByIdReglement() {
        FichierDetailsReglementDTO detailsDto = new FichierDetailsReglementDTO();
        detailsDto.setFichierEnteteReglement(1L);

        when(fichierDetailsReglementRepository.findByFichierEnteteReglement(1L))
                .thenReturn(Flux.just(EntityDtoUtil.fichierDetailsToEntity(detailsDto)));

        Flux<FichierDetailsReglementDTO> result = reglementService.getDetailsReglementByIdReglement(1L);

        StepVerifier.create(result)
                .assertNext(dto -> {
                    Assertions.assertNotNull(dto);
                    Assertions.assertEquals(1L, dto.getFichierEnteteReglement());
                })
                .verifyComplete();
    }

    @Test
    @Order(5)
    void testGetAll() {
        FichierEnteteReglementEntity entity = new FichierEnteteReglementEntity();
        entity.setId(1L);

        when(fichierEnteteReglementRepository.findAll())
                .thenReturn(Flux.just(entity));

        Flux<FichierEnteteReglementDTO> result = reglementService.getAll();

        StepVerifier.create(result)
                .assertNext(dto -> {
                    Assertions.assertNotNull(dto);
                    Assertions.assertEquals(1L, dto.getId());
                })
                .verifyComplete();
    }

    @Test
    @Order(6)
    void testDeleteReglementById() {
        when(fichierDetailsReglementRepository.findByFichierEnteteReglement(1L))
                .thenReturn(Flux.empty());
        when(fichierEnteteReglementRepository.deleteById(1L))
                .thenReturn(Mono.empty());

        Mono<Void> result = reglementService.deleteReglement(1L);

        StepVerifier.create(result)
                .verifyComplete();
    }
}
