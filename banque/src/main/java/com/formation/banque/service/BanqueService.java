package com.formation.banque.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.formation.banque.entity.Pays;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.formation.banque.dto.BanqueDto;
import com.formation.banque.dto.BanqueVo;
import com.formation.banque.dto.CompteReglementDto;
import com.formation.banque.entity.BanqueEntity;
import com.formation.banque.entity.CompteReglementEntity;
import com.formation.banque.mapper.BanqueMapper;
import com.formation.banque.repository.BanqueRepository;
import com.formation.banque.utils.FonctionnelleException;

import jakarta.annotation.Resource;

@Service
@Transactional
public class BanqueService {

	private BanqueRepository banqueRepository;

	private BanqueMapper mapper;

	private Pays[] mListePaysCache = new Pays[0];

	@Resource
	RestTemplate paysTemplate;

	private CircuitBreakerFactory cbFactory;

	public BanqueService(BanqueRepository banqueRepository, BanqueMapper mapper,
			CircuitBreakerFactory cbFactory) {
		super();
		this.banqueRepository = banqueRepository;
		this.mapper = mapper;
		this.cbFactory = cbFactory;
	}

	public BanqueVo createBanque(BanqueVo banque) throws FonctionnelleException {

		try {
			CompteReglementEntity compte = new CompteReglementEntity();
			compte.setReference(banque.getCompte().getReference());
			compte.setLibelle(banque.getCompte().getLibelle());
			compte.setSolde(banque.getCompte().getSolde());
			compte.setEtatCompte(banque.getCompte().getEtatCompte());

			BanqueEntity data = new BanqueEntity();
			data.setCodeBanque(banque.getCodeBanque());
			data.setLibelle(banque.getLibelle());
			data.setEtatBanque(banque.getEtatBanque());
			data.setCompte(compte);


			mListePaysCache = cbFactory.create("create-banque").run(
					() -> paysTemplate.getForObject("/entities/Pays", Pays[].class),
					throwable -> {
						System.out.println(throwable); 
						return paysFallBack();});

			Optional<Pays> pays = Arrays.asList(mListePaysCache).stream()
					.filter(item -> item.getCodeIso().equals(banque.getPaysCodeIso())).findFirst();

			if (pays.isPresent()) {
				data.setPays(pays.get());
			} else {
				throw new FonctionnelleException(
						"Il n'existe pas de pays avec le code Iso [" + banque.getPaysCodeIso() + "] dans le système");
			}

			return mapper.entityToVo(banqueRepository.save(data));

		} catch (FonctionnelleException e1) {
			throw new FonctionnelleException(e1.getMessage());
		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors de la création de la banque", e);
		}
	}

	private Pays[] paysFallBack() {

		return mListePaysCache;
	}

	public BanqueDto update(Long idBanque, BanqueDto banque) throws FonctionnelleException {

		try {

			BanqueEntity data = banqueRepository.findById(idBanque).orElse(new BanqueEntity());

			if (data != null) {

				data.getCompte().setReference(banque.getCompte().getReference());
				data.getCompte().setLibelle(banque.getCompte().getLibelle());
				data.getCompte().setSolde(banque.getCompte().getSolde());
				data.getCompte().setEtatCompte(banque.getCompte().getEtatCompte());

				data.setCodeBanque(banque.getCodeBanque());
				data.setLibelle(banque.getLibelle());
				data.setEtatBanque(banque.getEtatBanque());

				Pays pays = new Pays();
				pays.setCodeIso(banque.getPays().getCodeIso());
				pays.setLibelle(banque.getPays().getLibelle());

				data.setPays(pays);

				return mapper.entityToDto(banqueRepository.save(data));
			} else {
				throw new FonctionnelleException("La banque " + banque.getLibelle() + " n'existe pas dans la base");
			}

		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors de la mise à jour de banque", e);
		}
	}

	public List<BanqueVo> getBanqueBycriteria(int page, int pageSize) throws FonctionnelleException {

		try {

			Page<BanqueEntity> mListe = banqueRepository
					.findByCriteria(PageRequest.of(page, pageSize, Direction.ASC, "codeBanque"));

			return mapper.entitiesToVos(mListe);

		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors de récupération de banque", e);
		}
	}

	public BanqueDto getByCodeBanque(String codeBanque) throws FonctionnelleException {

		try {
			return mapper.entityToDto(banqueRepository.findByCodeBanque(codeBanque));

		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors de récupération de banque", e);
		}
	}

	public void delete(Long idBanque) throws FonctionnelleException {

		try {
			banqueRepository.deleteById(idBanque);
		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors de la suppression de la banque", e);
		}
	}

	public List<BanqueDto> getAll() throws FonctionnelleException {

		try {

			List<BanqueEntity> mListe = banqueRepository.findAll();

			List<BanqueDto> mResult = mListe.stream().map(e -> {
				return createBanqueDtoFromEntity(e);
			}).toList();

			return mResult;
		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors de récupération de banque", e);
		}
	}

	private BanqueDto createBanqueDtoFromEntity(BanqueEntity e) {
		CompteReglementDto compte = new CompteReglementDto();
		compte.setReference(e.getCompte().getReference());
		compte.setLibelle(e.getCompte().getLibelle());
		compte.setSolde(e.getCompte().getSolde());
		compte.setEtatCompte(e.getCompte().getEtatCompte());

		BanqueDto data = new BanqueDto();
		data.setCodeBanque(e.getCodeBanque());
		data.setLibelle(e.getLibelle());
		data.setEtatBanque(e.getEtatBanque());
		data.setCompte(compte);
		return data;
	}

}
