package org.formation.repository;

import org.formation.model.FichierDetailsReglementEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FichierDetailsReglementRepository extends ReactiveCrudRepository<FichierDetailsReglementEntity,Long> {
	
	@Query("select * from fichier_details_reglement where journeeCompensationId = :id")
	Flux<FichierDetailsReglementEntity> getReglementByJourneeCompense(long id);

	Flux<FichierDetailsReglementEntity> findByFichierEnteteReglement(long id);


}
