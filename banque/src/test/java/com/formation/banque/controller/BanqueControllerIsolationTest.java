package com.formation.banque.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formation.banque.entity.Pays;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {"spring.cloud.discovery.enabled=false","eureka.client.enabled=false"})
@AutoConfigureMockMvc
@Transactional
@AutoConfigureWireMock(port = 0) // Configuration automatique de WireMock
@EnableAutoConfiguration(exclude= EurekaClientAutoConfiguration.class)
public class BanqueControllerIsolationTest extends BanqueControllerIntegrationTest {

    private List<Pays> mListePays = new ArrayList<>();

    @TestConfiguration
    static class TestConfig {
        @Value("${wiremock.server.port}")
        String wiremockPort;
        @Bean
        @Primary
        public RestTemplate testRestTemplate(RestTemplateBuilder builder) {
            return builder.rootUri("http://localhost:"+wiremockPort).build();
        }
    }

    @BeforeEach
    void stubMock() throws JsonProcessingException {
        Pays pays = new Pays();
        pays.setCodeIso("CI");
        pays.setLibelle("Cote d'Ivoire");
        mListePays.add(pays);

        pays = new Pays();
        pays.setCodeIso("SN");
        pays.setLibelle("Sénégal");
        mListePays.add(pays);

        pays = new Pays();
        pays.setCodeIso("TG");
        pays.setLibelle("Togo");
        mListePays.add(pays);

        stubFor(WireMock.get(urlEqualTo("/entities/Pays")).
                willReturn(aResponse().withBody(mapper.writeValueAsString(mListePays))
                        .withHeader("Content-Type", "application/json").withFixedDelay(10)));
    }
}
