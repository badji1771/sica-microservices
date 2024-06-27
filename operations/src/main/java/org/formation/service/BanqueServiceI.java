package org.formation.service;

import java.util.List;

import org.formation.entities.Banque;

public interface  BanqueServiceI {

	List<Banque> getBanques() throws Exception;

	Banque getBanqueBycode(List<Banque> banques, String code);

}
