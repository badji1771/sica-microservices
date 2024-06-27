package org.formation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formation.entities.TypeRejet;
import org.formation.repository.TypeRejetRepository;
import org.formation.service.dto.typerejet.CriteriaTypeRejet;
import org.formation.service.dto.typerejet.TypeRejetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TypeRejetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private TypeRejetRepository typeRejetRepository;


    @BeforeEach
    public void setUp(){
        typeRejetRepository.save(TypeRejet.builder().type("ACT").code("TR01").libelle("Actif 1").etat("ACTIF").build());
        typeRejetRepository.save(TypeRejet.builder().type("RJT").code("TR02").libelle("Rejet 1").etat("REJET").build());
    }

    @Test
    void create() throws Exception {
        TypeRejetRequest request = new TypeRejetRequest("TR03", "Rejet 3", "REJET");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/type-rejets")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        TypeRejetRequest request = new TypeRejetRequest("TR01", "Rejet 4", "REJET");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/type-rejets")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/type-rejets/{code}","TR01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void getTypeRejet() throws Exception {
        CriteriaTypeRejet request =new CriteriaTypeRejet("TR01","","");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/type-rejets/search")
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