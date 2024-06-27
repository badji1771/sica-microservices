package org.formation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formation.entities.JourneeCompensation;
import org.formation.entities.TypeCompensation;
import org.formation.repository.JourneeCompensationRepository;
import org.formation.repository.TypeCompensationRepository;
import org.formation.service.dto.journeecompensation.JourCompensationRequest;
import org.formation.utils.Utulitaires;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class JourneeCompensationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    TypeCompensationRepository typeCompensationRepository;

    @Autowired
    JourneeCompensationRepository journeeCompensationRepository;

    @BeforeEach
    public void setUp() {

        TypeCompensation typeCompensation = typeCompensationRepository.findById("N").orElseThrow();
        journeeCompensationRepository.save(JourneeCompensation.builder()
                .dateJournee(new Date())
                .heureFermeture(new Date())
                .etat(String.valueOf(Utulitaires.ETAT.OUVERT))
                .typeCompensation(typeCompensation)
                .libelle("test 1")
                .build());

    }

    @Test
    void create() throws Exception {
        JourCompensationRequest request = new JourCompensationRequest(null, new Date(), "Test 2",new Date(),"N" );
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/journee-compensations")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        JourCompensationRequest request = new JourCompensationRequest(1L, new Date(), "Test 2",new Date(),"N" );
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/journee-compensations")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/journee-compensations/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void ouvrirJournee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/journee-compensations/{id}/ouvrir-journee",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void fermerJournee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/journee-compensations/{id}/fermer-journee",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}