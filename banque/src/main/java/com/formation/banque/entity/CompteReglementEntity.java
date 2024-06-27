package com.formation.banque.entity;

import java.math.BigDecimal;

import com.formation.banque.utils.Etat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "compte_reglement")
public class CompteReglementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String reference;

	@Column(nullable = false)
	private String libelle;

	@Column(nullable = false)
	private BigDecimal solde;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Etat etatCompte;

	@OneToOne(mappedBy = "compte")
	private BanqueEntity banque;

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

	public BanqueEntity getBanque() {
		return banque;
	}

	public void setBanque(BanqueEntity banque) {
		this.banque = banque;
	}

}
