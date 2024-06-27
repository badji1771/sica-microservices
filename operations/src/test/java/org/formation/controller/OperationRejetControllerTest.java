package org.formation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formation.entities.OperationRejet;
import org.formation.entities.TypeOperation;
import org.formation.entities.TypeRejet;
import org.formation.repository.OperationRejetRepository;
import org.formation.repository.TypeOperationRepository;
import org.formation.repository.TypeRejetRepository;
import org.formation.service.dto.operationrejet.OperationRejetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OperationRejetControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private OperationRejetRepository operationRejetRepository;

    @Autowired
    private TypeRejetRepository typeRejetRepository;

    @Autowired
    private TypeOperationRepository typeOperationRepository;

    private OperationRejet operationRejet;

    @BeforeEach
    public void setUp(){
        TypeRejet rejet= typeRejetRepository.save(TypeRejet.builder().type("ACT").code("TR01").libelle("Actif 1").etat("ACTIF").build());
        TypeOperation typeOperation=typeOperationRepository.findByCode("015");
        operationRejet = operationRejetRepository.save(OperationRejet.builder().rejet(rejet).typeOperation(typeOperation).build());
    }
    @Test
    void create() throws Exception {
        OperationRejetRequest request = new OperationRejetRequest(null, "TR01", "015");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/operation-rejets")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        OperationRejetRequest request = new OperationRejetRequest(operationRejet.getId(), "TR01", "015");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/operation-rejets")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/operation-rejets/{id}",operationRejet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void getOperationRejet() throws Exception {
        OperationRejetRequest request =new OperationRejetRequest(null,"TR01","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/operation-rejets/search")
                        .content(asJsonString(request))
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