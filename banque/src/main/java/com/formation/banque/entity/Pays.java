package com.formation.banque.entity;


import jakarta.persistence.*;

@Embeddable
public class Pays {

	private String codeIso;

	private String libelle;

	public String getCodeIso() {
		return codeIso;
	}

	public void setCodeIso(String codeIso) {
		this.codeIso = codeIso;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


}
