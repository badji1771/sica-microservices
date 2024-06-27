package com.formation.banque.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.banque.dto.BanqueDto;
import com.formation.banque.dto.BanqueVo;
import com.formation.banque.entity.BanqueEntity;
import com.formation.banque.entity.CompteReglementEntity;
import com.formation.banque.entity.Pays;
import com.formation.banque.mapper.BanqueMapper;
import com.formation.banque.repository.BanqueRepository;
import com.formation.banque.utils.Etat;


@SpringBootTest(properties = { "spring.jpa.properties.hibernate.hbm2ddl.import_files=" })
@AutoConfigureMockMvc
@Transactional
class BanqueControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BanqueRepository banqueRepository;

	@Autowired
	BanqueMapper mapperBannque;


	@Autowired
	ObjectMapper mapper;

	@Autowired
	private ObjectMapper objectMapper;


	private BanqueVo data;
	private BanqueEntity banqueEntity;
	private String codeBanque = "AA11GH99";
	private String reference = "Z00_EY_1030389";
	private String reference2 = "K00_PP_1030389";



	@BeforeEach
	public void setUp() throws IOException {

		banqueEntity = new BanqueEntity();

		CompteReglementEntity compte = new CompteReglementEntity();
		compte.setReference(reference);
		compte.setLibelle("Compte d'opération interne");
		compte.setSolde(new BigDecimal(19000000));
		compte.setEtatCompte(Etat.ACTIVE);

		banqueEntity.setCodeBanque(codeBanque);
		banqueEntity.setLibelle("Banque Atlantique");
		banqueEntity.setEtatBanque(Etat.BROUILLON);


		Pays pays = new Pays();
		pays.setCodeIso("CI");
		pays.setLibelle("Cote d'Ivoire");

		banqueEntity.setPays(pays);
		banqueEntity.setCompte(compte);

		data = mapperBannque.entityToVo(banqueEntity);

	}

	@Test
	void createAndGetBanqueByCodeBanque() throws JsonProcessingException, Exception {


		mockMvc.perform((post("/api/banques")).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(data))).andExpect(status().isCreated());

		mockMvc.perform(get("/api/banques/{codeBanque}", data.getCodeBanque()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

		BanqueEntity banqueEntity = banqueRepository.findByCodeBanque(codeBanque);

		assertThat(banqueEntity).isNotNull();

		assertThat(banqueEntity.getCompte().getReference()).isEqualTo(reference);
	}

	@Test
	void getAllBanque() throws Exception {
		mockMvc.perform(get("/api/banques").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getByIdBanque() throws Exception {

		banqueRepository.save(banqueEntity);

		mockMvc.perform(get("/api/banques").contentType(MediaType.APPLICATION_JSON).content("id : 1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getBanqueByCodeBanque() throws Exception {

		BanqueEntity mValue = banqueRepository.save(banqueEntity);

		mockMvc.perform(get("/api/banques/{codeBanque}", mValue.getCodeBanque()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void getCompteByIdBanque() throws Exception {

		BanqueEntity mValue = banqueRepository.save(banqueEntity);

		mockMvc.perform(get("/api/banques/{id}/compte", mValue.getId()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void updateBanque () throws Exception {

		BanqueEntity mValue = banqueRepository.save(banqueEntity);

		mValue.setEtatBanque(Etat.SUSPENDU);
		
		BanqueDto myData = mapperBannque.entityToDto(mValue);
		
		mockMvc.perform(put("/api/banques/{id}",mValue.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(myData))).andExpect(status().isAccepted());
	}

	@Test
	void getCompteByIdBanque_whenBadIdReturnNotAcceptable() throws Exception {

		mockMvc.perform(get("/api/banques/{id}/compte", 99l).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotAcceptable());

	}
	/*
	 * A faire mmarcher. j'ai l'impression que le roolback ne fonctionne pas
	 */

//	@Test
//	@Rollback
//	void getCompteByIdBanque_whenBadIdReturnNotAcceptable1() throws Exception {
//
//		banqueEntity.getCompte().setReference(reference2);
//		BanqueEntity mValue = banqueRepository.save(banqueEntity);
//
//		mockMvc.perform(get("/api/banques/{id}/compte", 1l).contentType(MediaType.APPLICATION_JSON)).andDo(print())
//				.andExpect(status().isOk()).andExpect(jsonPath("$.reference").value(mValue.getCompte().getReference()));
//
//	}
}
