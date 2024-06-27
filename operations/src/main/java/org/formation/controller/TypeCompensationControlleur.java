package org.formation.controller;

import java.util.List;

import org.formation.entities.TypeCompensation;
import org.formation.service.TypeCompensationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/type-compensations")
public class TypeCompensationControlleur {
	
	
	private final TypeCompensationService typeCompensationService;
	
	public TypeCompensationControlleur(TypeCompensationService typeCompensationService) {
		this.typeCompensationService = typeCompensationService;
	}

	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TypeCompensation createTypeCompensation(@RequestBody TypeCompensation typeCompensation) {
		return typeCompensationService.createTypeCompensation(typeCompensation);
	}
	
	@GetMapping
	public List<TypeCompensation> findAll() {
		return typeCompensationService.getAllTypeCompensation();
	}
	
	@GetMapping("/{code}")
	public TypeCompensation findTypeCompensationByCode(@PathVariable String code) {
		return typeCompensationService.findByCode(code);
				//.orElseThrow(() -> new EntityNotFoundException());
	}
}
