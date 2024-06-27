package org.formation.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "FICHIER_DETAILS_REGLEMENT")
public class FichierDetailsReglementEntity {
	
	@Id
	private long id;	
	
	@Column(value = "NU_JourneeCompensation_FK")
	private long journeeCompensationId;
	
	@Column(value = "NU_ID_SOLDE_COMPENSE")
	private long idSoldeCompense;
	
	@Column(value = "DA_DateOperation")
	private Date dateOperation;
	
	@Column(value = "CH_Banque_FK")
	private long banqueId;
	
	@Column(value = "NU_Solde")
	private BigDecimal solde;
	
	@Column(value = "CH_Etat_SOLDE_COMPENSATION")
	private String etatSoldeCompensation;
	
	@Column(value = "NU_FichierEnteteReglement_FK")
	private long fichierEnteteReglement;

}
