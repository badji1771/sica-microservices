package org.formation.sica.gatewaysica.test_api;


import org.formation.sica.gatewaysica.dto.JourneeCompensationResponse;
import org.formation.sica.gatewaysica.service.api.IJourneeCompensationApiService;
import org.formation.sica.gatewaysica.service.api.feign.JourneeCompensationFeignClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.AssertionErrors;

import java.util.Date;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        properties = {"spring.cloud.discovery.enabled=false","eureka.client.enabled=false"})
//@AutoConfigureMockMvc
//@Transactional
//@AutoConfigureWireMock(port = 0) // Configuration automatique de WireMock
//@EnableAutoConfiguration(exclude= EurekaClientAutoConfiguration.class)
@SpringBootTest
public class JourneeCompensationTestController {

    @MockBean
    private JourneeCompensationFeignClient journeeFeignClient;

    @Autowired
    private IJourneeCompensationApiService journeeService;

    private JourneeCompensationResponse journeeAttendue = new JourneeCompensationResponse();

    @Test
    public void testFetchData() {

        journeeAttendue.setLibelle("Journee 1");
        journeeAttendue.setTypeCompensationCode("NORMAL");
        journeeAttendue.setDateJournee(new Date());
        journeeAttendue.setEtat("OUVERT");
        // Arrange
        Mockito.when(journeeFeignClient.getJourneeById(1L)).thenReturn(ResponseEntity.ok(journeeAttendue));

        // Act
        JourneeCompensationResponse journee = journeeService.getJourneCompensationById(1L);

        // Assert
        AssertionErrors.assertEquals("Mocked Data", journeeAttendue, journee) ;
    }




}
