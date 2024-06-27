package com.formation.banque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formation.banque.dto.BanqueDto;
import com.formation.banque.dto.BanqueVo;
import com.formation.banque.dto.CompteReglementVo;
import com.formation.banque.service.BanqueService;
import com.formation.banque.service.CompteService;
import com.formation.banque.utils.FonctionnelleException;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/banques")
public class BanqueController {

	@Autowired
	BanqueService banqueService;

	@Autowired
	CompteService compteService;

	@GetMapping("/{codeBanque}")
	public ResponseEntity<BanqueDto> getBanque(@PathVariable String codeBanque) throws FonctionnelleException {

		BanqueDto data = banqueService.getByCodeBanque(codeBanque);
		return new ResponseEntity<>(data, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<BanqueVo> createBanque(@Valid @RequestBody BanqueVo banque) throws FonctionnelleException {

		BanqueVo data = banqueService.createBanque(banque);
		return new ResponseEntity<>(data, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<BanqueDto> update(@PathVariable Long id, @RequestBody BanqueDto banque) throws FonctionnelleException {

		BanqueDto data = banqueService.update(id, banque);
		return new ResponseEntity<BanqueDto>(data, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public ResponseEntity<Void> delete(@PathVariable Long id) throws FonctionnelleException {

		banqueService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<BanqueDto> getAll() throws FonctionnelleException {

		return banqueService.getAll();

	}

	@GetMapping("/criteria")
	@ResponseStatus(code = HttpStatus.OK)
	public List<BanqueVo> getBanqueBycriteria(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "name") String sortBy)
			throws FonctionnelleException {

		return banqueService.getBanqueBycriteria(page, pageSize);

	}

	@GetMapping("/{id}/compte")
	@ResponseStatus(code = HttpStatus.OK)
	public CompteReglementVo getCompteByIdBanque(@PathVariable("id") long id) throws FonctionnelleException {

		return compteService.getCompteByIdBanque(id);

	}

	@PutMapping("/{id}/compte")
	public ResponseEntity<CompteReglementVo> updateCompteByIdBanque(@PathVariable long id, CompteReglementVo compte) throws FonctionnelleException {

		CompteReglementVo data = compteService.update(id, compte);
		return new ResponseEntity<CompteReglementVo>(data, HttpStatus.ACCEPTED);

	}

}
