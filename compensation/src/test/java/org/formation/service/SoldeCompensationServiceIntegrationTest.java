package org.formation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formation.domain.SoldeCompensation;
import org.formation.dto.BanqueDto;
import org.formation.dto.JourneeDto;
import org.formation.dto.operation.Banque;
import org.formation.dto.operation.Operation;
import org.formation.dto.operation.TypeOperation;
import org.formation.util.Etat;
import org.formation.util.Utilitaire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
class SoldeCompensationServiceIntegrationTest {

    @Autowired
    SoldeCompensationService soldeCompensationService;

    @Autowired
    private ObjectMapper objectMapper;

    List<BanqueDto> banquesDto = new ArrayList<>();

    List<Operation> operations = new ArrayList<>();



    @BeforeEach
    void setUp() {

        banquesDto.add(BanqueDto.builder().codeBanque("SOURCE").etatBanque(Etat.ACTIVE).build());
        banquesDto.add(BanqueDto.builder().codeBanque("DEST").etatBanque(Etat.ACTIVE).build());

        operations.add(Operation.builder().banqueDestination(Banque.builder().code("DEST").build())
                .banqueSource(Banque.builder().code("SOURCE").build())
                .etat(Utilitaire.ETAT.ACCEPTE)
                .typeOperation(TypeOperation.builder().code("CODE").build())
                .build());
    }
    @Test
    void testCalculSoldeCompense() throws Exception {

        JourneeDto dto = JourneeDto.builder().id(1l).dateJournee(new Date()).etat("ETAT").build();
        List<SoldeCompensation> soldeCompensationList=soldeCompensationService.calculSoldeCompense(dto);

        assertNotNull(soldeCompensationList);
    }
}
