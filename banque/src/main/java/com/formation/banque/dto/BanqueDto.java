package com.formation.banque.dto;

import com.formation.banque.entity.Pays;
import com.formation.banque.utils.Etat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BanqueDto {
	
	@NotNull
	private Long id;

	@NotNull
	private String codeBanque;

	@NotBlank(message = "Le libelle de la banque est obligatoire")
	@Size(max = 255, message = "Le libelle ne peut excéder 255 caracteres")
	private String libelle;

	@NotNull
	private Etat etatBanque;

	@NotNull
	private CompteReglementDto compte;

	@NotNull
	private Pays pays;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeBanque() {
		return codeBanque;
	}

	public void setCodeBanque(String codeBanque) {
		this.codeBanque = codeBanque;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Etat getEtatBanque() {
		return etatBanque;
	}

	public void setEtatBanque(Etat etatBanque) {
		this.etatBanque = etatBanque;
	}

	public CompteReglementDto getCompte() {
		return compte;
	}

	public void setCompte(CompteReglementDto compte) {
		this.compte = compte;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;

	}

}
