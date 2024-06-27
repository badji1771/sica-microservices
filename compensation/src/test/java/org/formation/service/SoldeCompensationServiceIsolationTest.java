package org.formation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.formation.dto.BanqueDto;
import org.formation.dto.operation.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {"spring.cloud.discovery.enabled=false","eureka.client.enabled=false","spring.main.allow-bean-definition-overriding=true"})
@Transactional
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
class SoldeCompensationServiceIsolationTest extends SoldeCompensationServiceIntegrationTest {

    @Autowired
    SoldeCompensationService soldeCompensationService;

    @Autowired
    private ObjectMapper objectMapper;

    List<BanqueDto> banquesDto = new ArrayList<>();

    List<Operation> operations = new ArrayList<>();

    @TestConfiguration
    @Profile("test")
    static class TestConfig {
        @Value("${wiremock.server.port}")
        String wiremockPort;
        @Bean
        @Primary
        public RestTemplate banqueRestTemplate(RestTemplateBuilder builder) {
            return builder.rootUri("http://localhost:"+wiremockPort).build();
        }
        @Bean
        @Primary
        public RestTemplate operationRestTemplate(RestTemplateBuilder builder) {
            return builder.rootUri("http://localhost:"+wiremockPort).build();
        }
    }
    @BeforeEach
    void stubMock() throws JsonProcessingException {

        stubFor(WireMock.get(urlEqualTo("/api/banques")).
                willReturn(aResponse().withBody(objectMapper.writeValueAsString(banquesDto))
                        .withHeader("Content-Type", "application/json").withFixedDelay(10)));
        stubFor(WireMock.get(urlEqualTo("/api/operations/journee/1")).
                willReturn(aResponse().withBody(objectMapper.writeValueAsString(operations))
                        .withHeader("Content-Type", "application/json").withFixedDelay(10)));
    }

}
