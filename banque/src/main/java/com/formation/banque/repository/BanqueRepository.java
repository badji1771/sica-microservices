package com.formation.banque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formation.banque.entity.BanqueEntity;

public interface BanqueRepository extends JpaRepository<BanqueEntity, Long> {

	BanqueEntity findByCodeBanque(String codeBanque);

	@Query(value = "SELECT b FROM BanqueEntity b")
	Page<BanqueEntity> findByCriteria(final Pageable pageable);

}
