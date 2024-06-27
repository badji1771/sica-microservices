package org.formation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.formation.entities.TypeOperation;
import org.formation.service.TypeOperationService;
import org.formation.utils.Utulitaires.ETAT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TypeOperationControlleurTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	TypeOperationService tOperationService;

	@Test
	 void findTypeOperationByCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/type-operations/{code}", "CI015")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	 void findAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/type-operations").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	 void createTypeOperation() throws Exception {
		TypeOperation typeOperation = new TypeOperation("025", "Chèque",ETAT.VALIDE.name());
		mockMvc.perform(MockMvcRequestBuilders.post("/api/type-operations").content(asJsonString(typeOperation))
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
