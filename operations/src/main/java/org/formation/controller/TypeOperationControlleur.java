package org.formation.controller;

import java.util.List;

import org.formation.entities.TypeOperation;
import org.formation.service.TypeOperationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/type-operations")
public class TypeOperationControlleur {

	private final TypeOperationService typeOperationService;

	public TypeOperationControlleur(TypeOperationService typeOperationService) {
		this.typeOperationService = typeOperationService;
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TypeOperation createTypeOperation(@RequestBody TypeOperation request) {
		return typeOperationService.createTypeOperation(request);
	}

	@GetMapping
	public List<TypeOperation> findAll() {
		return typeOperationService.getAllTypeOperation();
	}

	@GetMapping("/{code}")
	public TypeOperation findTypeOperationByCode(@PathVariable String code) {
		return typeOperationService.findByCode(code);
	}

}
