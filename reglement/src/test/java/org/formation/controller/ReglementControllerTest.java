package org.formation.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.formation.dto.FichierDetailsReglementDTO;
import org.formation.dto.FichierEnteteReglementDTO;
import org.formation.dto.SoldeCompensationDTO;
import org.formation.service.ReglementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Flux;

@SpringBootTest
@AutoConfigureWebTestClient
/**
 * Nécessite que la base soit vide :
 * docker-compose down --volumes
 * docker-compose up -d
 */
class ReglementControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @InjectMocks
    private ReglementService reglementService;

    private static long idFichierEnteteReglement;

    @Test
    @Order(1)
    @WithMockUser(authorities = {"SCOPE_service"})
    void testCreateReglement() throws Exception {
        SoldeCompensationDTO soldeCompensation = SoldeCompensationDTO.builder()
                .idSoldeCompense(2)
                .dateOperation(LocalDate.now())
                .banqueId(2)
                .solde(new BigDecimal(100000))
                .etatSoldeCompensation("AT")
                .journeeCompensationId(5)
                .build();

        Flux<SoldeCompensationDTO> soldeCompensationFlux = Flux.just(soldeCompensation);

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(soldeCompensationFlux.collectList().block());
        System.out.println("request body: " + requestBody);
        webTestClient.post().uri("/api/reglements")
                .contentType(MediaType.APPLICATION_JSON)
                .body(soldeCompensationFlux, SoldeCompensationDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(FichierEnteteReglementDTO.class)
                .consumeWith(response -> {
                    FichierEnteteReglementDTO enteteReglement = response.getResponseBody();
                    assert enteteReglement != null;
                    idFichierEnteteReglement = enteteReglement.getId();
                    Assertions.assertNotNull(enteteReglement);
                    Assertions.assertNotNull(enteteReglement.getDateCreation());
                    Assertions.assertNotNull(enteteReglement.getNomFichier());
                });
    }

    @Test
    @Order(2)
    @WithMockUser(authorities = {"SCOPE_service"})
    void testGetReglementById() {
        System.out.println("id fichier "+ idFichierEnteteReglement);
        webTestClient.get().uri("/api/reglements/"+idFichierEnteteReglement)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(FichierEnteteReglementDTO.class)
                .hasSize(1);
    }


    @Test
    @Order(3)
    @WithMockUser(authorities = {"SCOPE_service"})
    void testGetReglementByJourneeCompensation() {

        webTestClient.get().uri("/api/reglements/criteria/5")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(FichierEnteteReglementDTO.class)
                .returnResult();
    }

    @Test
    @Order(4)
    @WithMockUser(authorities = {"SCOPE_service"})
    void testGetDetailsReglementByIdReglement() {

        webTestClient.get().uri("/api/reglements/criteria/details/"+idFichierEnteteReglement)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(FichierDetailsReglementDTO.class)
                .returnResult();
    }

    @Test
    @Order(5)
    @WithMockUser(authorities = {"SCOPE_service"})
    void testDeleteReglementById() {
        webTestClient.delete().uri("/api/reglements/"+idFichierEnteteReglement)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }
}
