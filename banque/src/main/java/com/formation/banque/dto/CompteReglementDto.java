package com.formation.banque.dto;

import java.math.BigDecimal;

import com.formation.banque.utils.Etat;

import jakarta.validation.constraints.NotNull;

public class CompteReglementDto {
	
	@NotNull
	private Long id;

	@NotNull
	private String reference;

	private String libelle;

	@NotNull
	private BigDecimal solde;

	@NotNull
	private Etat etatCompte;

	@NotNull
	private BanqueDto banque;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public BanqueDto getBanque() {
		return banque;
	}

	public void setBanque(BanqueDto banque) {
		this.banque = banque;
	}

}
