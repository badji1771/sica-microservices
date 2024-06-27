package org.formation.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.formation.entities.Banque;
import org.formation.entities.Operation;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OperationServiceTest {

    @Autowired OperationService operationService;

    @MockBean BanqueService banqueService ;

    Banque[]  banques ;
    @BeforeEach
    public void setUp() {
        Resource resource = new ClassPathResource("/banques.json");
        try {
            String banquesString = resource.getContentAsString(Charset.defaultCharset()) ;
            ObjectMapper objectMapper = new ObjectMapper();
             banques = objectMapper.readValue(banquesString, Banque[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fileToOperation() throws Exception {
       Mockito.when(banqueService.getBanques())
               .thenReturn(Arrays.asList( this.banques));

       List<Operation>  operations = operationService.fileToOperation("src/main/resources/ICOM1");
       Assertions.assertThat(operations).isNotNull();
    }



}
