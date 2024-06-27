package org.formation.repository;

import org.formation.entities.TypeCompensation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCompensationRepository extends JpaRepository<TypeCompensation, String> {

	TypeCompensation findByCode(String code);

}
