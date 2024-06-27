package org.formation.service;

import java.util.List;

import org.formation.entities.TypeOperation;
import org.formation.repository.TypeOperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeOperationService {

	private final TypeOperationRepository typeOperationRepository;


	public TypeOperationService(TypeOperationRepository typeOperationRepository) {
		super();
		this.typeOperationRepository = typeOperationRepository;
	}


	public TypeOperation createTypeOperation(TypeOperation typeOperation) {
		return typeOperationRepository.save(typeOperation);
	}
	
	public List<TypeOperation> getAllTypeOperation ( ) {
		return  typeOperationRepository.findAll() ;
	}

	public TypeOperation findByCode(String code) {
		
		return typeOperationRepository.findByCode(code);
	}
	

}
