
package com.formation.banque.entity;

import com.formation.banque.utils.Etat;

import jakarta.persistence.*;

@Entity
@Table(name = "banque")
public class BanqueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String codeBanque;

	@Column(nullable = false, unique = true)
	private String libelle;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Etat etatBanque;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_compte", referencedColumnName = "id")
	private CompteReglementEntity compte;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "codeIso", column = @Column(name = "pays_codeIso")),
			@AttributeOverride(name = "libelle", column = @Column(name = "pays_libelle"))
	})
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

	public CompteReglementEntity getCompte() {
		return compte;
	}

	public void setCompte(CompteReglementEntity compte) {
		this.compte = compte;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;

	}

}
