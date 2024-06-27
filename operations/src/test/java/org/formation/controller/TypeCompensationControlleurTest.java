package org.formation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.formation.entities.TypeCompensation;
import org.formation.service.TypeCompensationService;
import org.formation.service.dto.journeecompensation.JourCompensationRequest;
import org.formation.utils.Utulitaires;
import org.formation.utils.Utulitaires.ETAT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class TypeCompensationControlleurTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	TypeCompensationService typeCompensationService;

	@Test
	void findByCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/type-compensations/{code}", "N")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	void findAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/type-compensations").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void createTypeCompensation() throws Exception {
		TypeCompensation typeCompensation = new TypeCompensation();
		typeCompensation.setCode("N");
		typeCompensation.setLibelle(Utulitaires.TYPE_CONPENSATION.NATIONALE.name());
		typeCompensation.setEtat(ETAT.VALIDE.name());

		//typeCompensationService.createTypeCompensation(typeCompensation);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/type-compensations").content(asJsonString(typeCompensation))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());

	}



	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
