package org.formation.repository;

import org.formation.model.FichierEnteteReglementEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FichierEnteteReglementRepository extends ReactiveCrudRepository<FichierEnteteReglementEntity,Long> {

    Flux<FichierEnteteReglementEntity> findByIdJourneeCompense(long idJourneeCompense);

}
