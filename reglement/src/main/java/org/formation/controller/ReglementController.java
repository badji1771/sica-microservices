package org.formation.controller;

import org.formation.dto.FichierDetailsReglementDTO;
import org.formation.dto.FichierEnteteReglementDTO;
import org.formation.dto.SoldeCompensationDTO;
import org.formation.service.IReglementService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reglements")
public class ReglementController {
	
	IReglementService reglementService;
	
	public ReglementController(IReglementService fichierEnteteReglementService) {
		this.reglementService = fichierEnteteReglementService;

	}
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('SCOPE_service')")
	public Flux<FichierEnteteReglementDTO> getAllReglement() {
		return this.reglementService.getAll();
		
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Mono<FichierEnteteReglementDTO> createReglement(@RequestBody SoldeCompensationDTO[] soldeCompensations) {
		return this.reglementService.createReglement(soldeCompensations);
		
	}
	
	@GetMapping("/{id}")
	public Mono<FichierEnteteReglementDTO> getReglementById(@PathVariable long id) {
		return this.reglementService.getReglementById(id);
		
	}

	@PutMapping("/{id}")
	public Mono<FichierEnteteReglementDTO> updateReglement(@PathVariable long id, @RequestBody FichierEnteteReglementDTO reglementDTO) {
		return this.reglementService.updateReglement(id, reglementDTO);

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Mono<Void> deleteReglement(@PathVariable long id) {
		return this.reglementService.deleteReglement(id);
	}
	
	@GetMapping("/criteria/{id}")
	@PreAuthorize("hasAuthority('SCOPE_service')")
	public Flux<FichierEnteteReglementDTO> getReglementByJourneeCompensation(@PathVariable long id){
		return this.reglementService.getReglementByJourneeCompensation(id);
		
	}

	@GetMapping("/criteria/details/{id}")
	@PreAuthorize("hasAuthority('SCOPE_service')")
	public Flux<FichierDetailsReglementDTO> getDetailsReglementByIdReglement(@PathVariable long id){
		return this.reglementService.getDetailsReglementByIdReglement(id);

	}
}
