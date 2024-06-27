package org.formation.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.netty.handler.logging.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;


@Service
public class KeycloakTokenService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakAuthServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.client-id}")
    private String keycloakClientId;

    @Value("${keycloak.client-secret}")
    private String keycloakClientSecret;

    public String getAccessToken() {
        HttpClient httpClient = HttpClient.create().wiretap("reactor.netty.http.client.HttpClient",
                LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(keycloakAuthServerUrl).build();


        return webClient.post()
                .uri("/realms/{realm}/protocol/openid-connect/token", keycloakRealm)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", keycloakClientId)
                        .with("client_secret", keycloakClientSecret))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(i -> i.path("access_token").asText()).block();

    }
}

