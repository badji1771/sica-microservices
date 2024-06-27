package org.formation.entities;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Banque {

	private String code ;
	
	private String abrege ;
	
	private String libelle ;
	
	private String compteReglement ;
	
	private String etat ;

	public Banque(String code, String abrege, String libelle, String compteReglement, String etat) {
		super();
		this.code = code;
		this.abrege = abrege;
		this.libelle = libelle;
		this.compteReglement = compteReglement;
		this.etat = etat;
	}

	public Banque() {
		super();
	}

}
