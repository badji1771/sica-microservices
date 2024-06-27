package com.formation.banque.dto;

import java.math.BigDecimal;

import com.formation.banque.utils.Etat;

import jakarta.validation.constraints.NotNull;

public class CompteReglementVo {

	@NotNull
	private String reference;

	private String libelle;

	@NotNull
	private BigDecimal solde;

	@NotNull
	private Etat etatCompte;
 
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public BigDecimal getSolde() {
		return solde;
	}

	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}

	public Etat getEtatCompte() {
		return etatCompte;
	}

	public void setEtatCompte(Etat etatCompte) {
		this.etatCompte = etatCompte;
	}
}
