package org.formation.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.formation.entities.*;
import org.formation.repository.*;
import org.formation.service.BanqueServiceI;
import org.formation.service.dto.operation.OperationRequestDTO;
import org.formation.utils.Utulitaires;
import org.formation.utils.Utulitaires.ETAT;
import org.junit.jupiter.api.BeforeEach;
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
public class OperationControlleurTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	OperationRepository operationRepository;

	@Autowired
	JourneeCompensationRepository journeeCompensationRepository;

	@Autowired
	TypeCompensationRepository typeCompensationRepository;
	@Autowired
	TypeRejetRepository typeRejetRepository;

	@Autowired
	BanqueServiceI banqueService;

	@Autowired
	TypeOperationRepository typeOperationRepository;


	@BeforeEach
	public void setUp() {
		typeOperationRepository
				.saveAll(Arrays.asList(new TypeOperation("015", "Virement clientèle", ETAT.ACTIVE.name()),
						new TypeOperation("025", "Chèque ", ETAT.ACTIVE.name()),
						new TypeOperation("011", "Virement Banque", ETAT.ACTIVE.name())));
		TypeCompensation typeCompensation = typeCompensationRepository.findById("N").orElseThrow();
		journeeCompensationRepository.save(JourneeCompensation.builder()
				.dateJournee(new Date())
				.heureFermeture(new Date())
				.etat(String.valueOf(Utulitaires.ETAT.OUVERT))
				.typeCompensation(typeCompensation)
				.libelle("test 1")
				.build());

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		date.setHours(10);
		date.setMinutes(00);
		JourneeCompensation journeeCompensation = journeeCompensationRepository.findById(1L).orElseThrow();

		journeeCompensation.setLibelle("Journéee du  " + dateFormat.format(date));
		journeeCompensation.setTypeCompensation(typeCompensationRepository.findByCode("N"));
		journeeCompensation.setEtat(ETAT.OUVERT.name());
		journeeCompensation =journeeCompensationRepository.save(journeeCompensation);

		Operation operation = Operation.builder()
				.etat(ETAT.ACCEPTE)
				.codeBanqueDestination("BANQUE_DEST")
				.codeBanqueSource("BANQUE_SOURCE")
				.reference("REF")
				.journeeCompensation(journeeCompensation)
				.build();

		operationRepository.save(operation);

		TypeRejet tr1 = new TypeRejet();
		TypeRejet tr2 = new TypeRejet();
		TypeRejet tr3 = new TypeRejet();
		tr1.setCode("99");
		tr2.setCode("00");
		tr3.setCode("3");


		tr1.setLibelle("rejet 1");
		tr2.setLibelle("rejet 2");
		tr3.setLibelle("rejet 3");

		typeRejetRepository.saveAll(Arrays.asList(tr1, tr2, tr3));
	}

	@Test
	void getAllOperation() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/operations").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}
	/*@Test
	void getBanqueBycode() throws Exception {
		List<Banque> banques = banqueService.getBanques();
		banqueService.getBanqueBycode(banques, "CI001");
	}*/

	@Test
	 void findById() throws Exception {
		Operation operation = Operation.builder()
				.etat(ETAT.ACCEPTE)
				.codeBanqueDestination("BANQUE_DEST")
				.codeBanqueSource("BANQUE_SOURCE")
				.reference("REF")
				.typeOperation(typeOperationRepository.findByCode("015"))
				.build();
		operationRepository.save(operation);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/operations/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.reference").value("REF"))
				.andExpect(jsonPath("$.etat").value(ETAT.ACCEPTE.name()));

	}
	@Test
	void rejetOperation() throws Exception {
		JourneeCompensation journeeCompensation = journeeCompensationRepository.findById(1L).orElseThrow();

		mockMvc.perform(MockMvcRequestBuilders
						.put("/api/operations/rejet/{id}",1L)
						.param("codeRejet","99")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}
	@Test
	void getOperationsParJourneeCompense() throws Exception {
		OperationRequestDTO operationRequestDTO = new OperationRequestDTO();
		operationRequestDTO.setCodeBanque("BANQUE_SOURCE");
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/operations/journee/{idCompense}",1L)
						.content(asJsonString(operationRequestDTO))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void getOperationsParJourneeCompense1() throws Exception {
		OperationRequestDTO operationRequestDTO = new OperationRequestDTO();
		operationRequestDTO.setCodeBanque("BANQUE_SOURCE");
		operationRequestDTO.setTypeOperation("015");
		operationRequestDTO.setSensBanque(Utulitaires.SENS_BANQUE.S);
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/operations/journee/{idCompense}",1L)
						.content(asJsonString(operationRequestDTO))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	
}
