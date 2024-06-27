package org.formation.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReglementDTO {
	
	private String nomFichier;
	
	private LocalDate dateCreation;
	
	private List<FichierDetailsReglementDTO> fichierDetailsReglementDTOs;

}
