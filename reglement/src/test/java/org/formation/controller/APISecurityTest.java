package org.formation.controller;

import org.formation.dto.SoldeCompensationDTO;
import org.formation.service.KeycloakTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureWebTestClient
public class APISecurityTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private KeycloakTokenService keycloakTokenService;

    @Test
    public void createReglementEndpointShouldBeAccessible() {
        SoldeCompensationDTO soldeCompensation = SoldeCompensationDTO.builder()
                .idSoldeCompense(2)
                .dateOperation(LocalDate.now())
                .banqueId(2)
                .solde(new BigDecimal(5000))
                .etatSoldeCompensation("AT")
                .journeeCompensationId(6)
                .build();

        Flux<SoldeCompensationDTO> soldeCompensationFlux = Flux.just(soldeCompensation);

        String accessToken = keycloakTokenService.getAccessToken();

        webTestClient.post().uri("/api/reglements")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(soldeCompensationFlux, SoldeCompensationDTO.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void getAllReglementEndpointShouldRequireScopeService() {
        webTestClient.get().uri("/api/reglements")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    public void getAllReglementEndpointShouldBeAccessibleWithValidAccessToken() {
        String accessToken = keycloakTokenService.getAccessToken();

        webTestClient.get().uri("/api/reglements")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .exchange()
                .expectStatus().isOk();
    }

}
