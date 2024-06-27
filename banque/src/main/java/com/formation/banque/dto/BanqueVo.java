package com.formation.banque.dto;

import com.formation.banque.utils.Etat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BanqueVo {

	@NotNull
	private String codeBanque;

	@NotBlank(message = "Le libelle de la banque est obligatoire")
	@Size(max = 255, message = "Le libelle ne peut excéder 255 caracteres")
	private String libelle;

	@NotNull
	private Etat etatBanque;

	@NotNull
	private CompteReglementVo compte;

	@NotNull
	private String paysCodeIso;

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

	public CompteReglementVo getCompte() {
		return compte;
	}

	public void setCompte(CompteReglementVo compte) {
		this.compte = compte;
	}

	public String getPaysCodeIso() {
		return paysCodeIso;
	}

	public void setPaysCodeIso(String paysCodeIso) {
		this.paysCodeIso = paysCodeIso;
	}

}
