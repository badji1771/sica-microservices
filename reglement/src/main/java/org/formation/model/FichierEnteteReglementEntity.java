package org.formation.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;


@Data
@Table(name = "FichierEnteteReglement")
public class FichierEnteteReglementEntity {
	
	@Id
	private long id;	
	
	@Column(value = "CH_NomFichier")
	private String nomFichier;	
	
	@Column(value = "DA_DateCreation")
	private LocalDate dateCreation;

	@Column(value = "ID_JOURNEE_COMPENSE")
	private long idJourneeCompense;

}
