package com.formation.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.banque.entity.CompteReglementEntity;

public interface CompteReglementRepository extends JpaRepository<CompteReglementEntity, Long> {

	// @Query("from CompteReglementEntity c where c.banque.id = :idBanque ")
	CompteReglementEntity findByBanque_Id(Long idBanque);
}
