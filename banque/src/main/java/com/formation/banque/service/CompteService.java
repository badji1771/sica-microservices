package com.formation.banque.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.banque.dto.CompteReglementVo;
import com.formation.banque.entity.CompteReglementEntity;
import com.formation.banque.repository.CompteReglementRepository;
import com.formation.banque.utils.FonctionnelleException;

@Service
@Transactional
public class CompteService {

	private CompteReglementRepository compteReglementRepository;

	public CompteService(CompteReglementRepository compteReglementRepository) {
		super();
		this.compteReglementRepository = compteReglementRepository;
	}

	public CompteReglementVo getCompteByIdBanque(Long idBanque) throws FonctionnelleException {

		try {
			CompteReglementEntity entity = compteReglementRepository.findByBanque_Id(idBanque);

			if (entity == null) {
				throw new FonctionnelleException("Aucun compte n'existe pour cet établissement");
			}

			CompteReglementVo data = new CompteReglementVo();
			data.setReference(entity.getReference());
			data.setLibelle(entity.getLibelle());
			data.setSolde(entity.getSolde());
			data.setEtatCompte(entity.getEtatCompte());
			return data;

		} catch (FonctionnelleException e) {
			throw new FonctionnelleException(e.getMessage());
		} catch (Exception e1) {
			throw new FonctionnelleException("Erreur lors de la récupération du compte", e1);
		}
	}

	public CompteReglementVo update(Long idBanque, CompteReglementVo compte) throws FonctionnelleException {

		try {

			CompteReglementEntity entity = compteReglementRepository.findByBanque_Id(idBanque);

			entity.setReference(compte.getReference());
			entity.setLibelle(compte.getLibelle());
			entity.setEtatCompte(compte.getEtatCompte());
			entity.setSolde(compte.getSolde());

			compteReglementRepository.save(entity);

			return compte;
		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors de la modification du compte", e);
		}
	}

}
