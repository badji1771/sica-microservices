package org.formation.service;

import org.formation.dto.FichierDetailsReglementDTO;
import org.formation.dto.FichierEnteteReglementDTO;
import org.formation.dto.SoldeCompensationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IReglementService {

	Flux<FichierDetailsReglementDTO> getDetailsReglementByIdReglement(long id);

	Flux<FichierEnteteReglementDTO> getAll();
	
	Mono<FichierEnteteReglementDTO> createReglement(SoldeCompensationDTO[] soldeCompensations);
	
	Mono<FichierEnteteReglementDTO> updateReglement( long id, FichierEnteteReglementDTO reglementDTO);
	
	Mono<FichierEnteteReglementDTO> getReglementById( long id);

	Mono<Void> deleteReglement(long id);

	Flux<FichierEnteteReglementDTO> getReglementByJourneeCompensation(long id);

}
