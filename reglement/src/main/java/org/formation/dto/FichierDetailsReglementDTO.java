package org.formation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class FichierDetailsReglementDTO {
	
	private long id;

	private long journeeCompensationId;
	
	private long idSoldeCompense;
	
	private LocalDate dateOperation;
	
	private long banqueId;
	
	private BigDecimal solde;
	
	private String etatSoldeCompensation;
	
	private long fichierEnteteReglement;
}
