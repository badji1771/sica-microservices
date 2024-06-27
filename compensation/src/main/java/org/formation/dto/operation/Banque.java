package org.formation.dto.operation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banque {
	

	private String code ;
	
	private String abrege ;
	
	private String libelle ;
	
	private String compteReglement ;
	
	private String etat ;

	
	

}
