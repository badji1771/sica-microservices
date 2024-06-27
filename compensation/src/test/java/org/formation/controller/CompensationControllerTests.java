package org.formation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompensationControllerTests {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetSoldeParPeriodeEtParTypeOperation() throws Exception {



        mockMvc.perform(get("/api/compensations/soldes")
                        .param("periode", "2024-03-25")
                        .param("typeOperation", "Virement")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].nuJourneeCompense").value(7))
                        .andExpect(jsonPath("$[0].codeBanque").value("CBAO"))
                        .andExpect(jsonPath("$[0].soldeCompense").value(25000))
                        .andExpect(jsonPath("$[0].etat").value("ACTIF"));;


    }

    @Test
    public void testGetSoldeParPeriodeEtParTypeOperationWithoutTypeOperation() throws Exception {



        mockMvc.perform(get("/api/compensations/soldes")
                        .param("periode", "2024-03-25")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].nuJourneeCompense").value(1))
                        .andExpect(jsonPath("$[0].codeBanque").value("CBAO"))
                        .andExpect(jsonPath("$[0].soldeCompense").value(395000))
                        .andExpect(jsonPath("$[0].etat").value("ACTIF"));;


    }

    @Test
    public void testGetSoldeParPeriodeEtParTypeOpEtBanque() throws Exception {
        mockMvc.perform(get("/api/compensations/soldes/{codeBanque}", "BHS")
                        .param("periode", "2024-03-29")
                        .param("typeOperation", "Virement")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].nuJourneeCompense").value("8"))
                        .andExpect(jsonPath("$[0].codeBanque").value("BHS"))
                        .andExpect(jsonPath("$[0].soldeCompense").value(25000))
                        .andExpect(jsonPath("$[0].etat").value("ACTIF"));

    }

    @Test
    public void testGetSoldeParPeriodeEtParTypeOpEtBanqueWithoutTypeOperation() throws Exception {
        mockMvc.perform(get("/api/compensations/soldes/{codeBanque}", "BHS")
                        .param("periode", "2024-03-29")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nuJourneeCompense").value("5"))
                .andExpect(jsonPath("$[0].codeBanque").value("BHS"))
                .andExpect(jsonPath("$[0].soldeCompense").value(100000))
                .andExpect(jsonPath("$[0].etat").value("ACTIF"));

    }
}
