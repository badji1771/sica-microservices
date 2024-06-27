package org.formation.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.formation.entities.Banque;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.Resource;
import lombok.extern.java.Log;

@Service
@Transactional
@Log
public class BanqueService implements BanqueServiceI {
	private final CircuitBreakerFactory cbFactory;

	@Resource
	RestTemplate restTemplate;

	@Resource
	RestTemplate banqueRestTemplate;

	public BanqueService(CircuitBreakerFactory cbFactory) {
		super();
		this.cbFactory = cbFactory;
	}

	@Override
	public List<Banque> getBanques() throws Exception {
		log.info("Recuparation  des banques");
		Banque[] banques = null;
		try {

			banques = cbFactory.create("loadAlBanque")
					.run(() -> banqueRestTemplate.getForObject("/api/banques", Banque[].class), t -> {
						log.warning("FALLBACK " + t);
						return null;
					});
			return Arrays.asList(banques);
		} catch (Exception e) {

			return Arrays.asList(banques);
		}
	}


	@Override
	public Banque getBanqueBycode(List<Banque> banques, String code) {
		Banque banque = null;
		for (Iterator iterator = banques.iterator(); iterator.hasNext();) {
			banque = (Banque) iterator.next();
			if (banque.getCode().trim().equals(code.trim())) {
				break;
			}
		}
		return banque;
	}

}
