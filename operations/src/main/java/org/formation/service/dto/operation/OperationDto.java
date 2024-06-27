package org.formation.service.dto.operation;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class OperationDto {

	@Id
	private Long id ;
	
	private Long journeeId ;
	
	private String reference;
	
	private String codeTypeOperation;
	
	private String codeBanqueSource;
	
	private String codeBanqueDestination;
	
	private String formuleNumerote;
	
	private String numeroCompteSource;
	
	private String numeroCompteDestination;
	
	private BigDecimal montant ;
	
	private String motif ;
	
	private Date dateReglement ;
	
	private String etat ;
	

}
