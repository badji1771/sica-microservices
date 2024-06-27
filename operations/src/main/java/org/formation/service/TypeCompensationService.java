package org.formation.service;

import java.util.List;

import org.formation.entities.TypeCompensation;
import org.formation.repository.TypeCompensationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeCompensationService {

	
	private final TypeCompensationRepository typeCompensationRepository;

	public TypeCompensationService(TypeCompensationRepository typeCompensationRepository) {
		super();
		this.typeCompensationRepository = typeCompensationRepository;
	}
	
	public TypeCompensation createTypeCompensation(TypeCompensation typeCompensation) {
		return typeCompensationRepository.save(typeCompensation);
	}
	public TypeCompensation findByCode(String code) {
		return typeCompensationRepository.findByCode( code);
	
	}
	
	public List<TypeCompensation> getAllTypeCompensation ( ) {
		return  typeCompensationRepository.findAll() ;	
	}

	
}
