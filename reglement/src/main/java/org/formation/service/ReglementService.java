package org.formation.service;

import org.formation.dto.FichierDetailsReglementDTO;
import org.formation.dto.FichierEnteteReglementDTO;
import org.formation.dto.SoldeCompensationDTO;
import org.formation.repository.FichierDetailsReglementRepository;
import org.formation.repository.FichierEnteteReglementRepository;
import org.formation.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@Service
public class ReglementService implements IReglementService {

	FichierEnteteReglementRepository fichierEnteteReglementRepository;
	FichierDetailsReglementRepository fichierDetailsReglementRepository;

	public ReglementService(FichierEnteteReglementRepository fichierEnteteReglementRepository
			, FichierDetailsReglementRepository fichierDetailsReglementRepository) {
		this.fichierEnteteReglementRepository = fichierEnteteReglementRepository;
		this.fichierDetailsReglementRepository = fichierDetailsReglementRepository;
	}


	@Override
	public Mono<FichierEnteteReglementDTO> createReglement(SoldeCompensationDTO[] soldeCompensations) {

		FichierEnteteReglementDTO fichierEnteteReglementDTO = new FichierEnteteReglementDTO();
		fichierEnteteReglementDTO.setDateCreation(LocalDate.now());
		fichierEnteteReglementDTO.setNomFichier("compensation_" + fichierEnteteReglementDTO.getDateCreation());
		fichierEnteteReglementDTO.setIdJourneeCompense(soldeCompensations[0].getJourneeCompensationId());


		return fichierEnteteReglementRepository
				.save(EntityDtoUtil.fichierEnteteToEntity(fichierEnteteReglementDTO))
				.doOnNext(enteteEntity ->   {
					for(SoldeCompensationDTO dto : soldeCompensations) {
						FichierDetailsReglementDTO fichierDetails = new FichierDetailsReglementDTO();
						fichierDetails.setBanqueId(dto.getBanqueId());
						fichierDetails.setDateOperation(dto.getDateOperation());
						fichierDetails.setEtatSoldeCompensation(dto.getEtatSoldeCompensation());
						fichierDetails.setFichierEnteteReglement(enteteEntity.getId());
						fichierDetails.setIdSoldeCompense(dto.getIdSoldeCompense());
						fichierDetails.setJourneeCompensationId(dto.getJourneeCompensationId());
						fichierDetails.setSolde(dto.getSolde());
						fichierDetailsReglementRepository.save(EntityDtoUtil.fichierDetailsToEntity(fichierDetails)).subscribe();
					}
				})
				.map(EntityDtoUtil::fichierEnteteToDto);

	}

	@Override
	public Mono<FichierEnteteReglementDTO> updateReglement(long id, FichierEnteteReglementDTO reglementDTO) {
		return null;
	}

	@Override
	public Mono<FichierEnteteReglementDTO> getReglementById(long id) {
		return fichierEnteteReglementRepository.findById(id)
				.map(EntityDtoUtil::fichierEnteteToDto);
	}

	@Override
	public Mono<Void> deleteReglement(long id) {
		return fichierDetailsReglementRepository.findByFichierEnteteReglement(id)
				.flatMap(fichierDetailsReglementRepository::delete)
				.then(fichierEnteteReglementRepository.deleteById(id));
	}

	@Override
	public Flux<FichierEnteteReglementDTO> getReglementByJourneeCompensation(long id) {

		return fichierEnteteReglementRepository.findByIdJourneeCompense(id).map(EntityDtoUtil::fichierEnteteToDto);
	}

	@Override
	public Flux<FichierDetailsReglementDTO> getDetailsReglementByIdReglement(long id) {

		Flux<FichierDetailsReglementDTO> map = fichierDetailsReglementRepository.findByFichierEnteteReglement(id).map(EntityDtoUtil::fichierDetailsToDto);
		return map;
	}


	@Override
	public Flux<FichierEnteteReglementDTO> getAll() {
		return fichierEnteteReglementRepository.findAll().map(EntityDtoUtil::fichierEnteteToDto);
	}


}
