package org.formation.repository;

import org.formation.entities.TypeOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeOperationRepository extends JpaRepository<TypeOperation, String> {

	TypeOperation findByCode(String code);

}
