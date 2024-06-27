package org.formation.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class FichierEnteteReglementDTO {
	
	private long id;
	
	private String nomFichier;
	
	private LocalDate dateCreation;

	private long idJourneeCompense;

}
