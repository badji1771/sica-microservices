package org.formation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SoldeCompensationDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private long journeeCompensationId;
	
	private long idSoldeCompense;
	
	private LocalDate dateOperation;
	
	private long banqueId;
	
	private BigDecimal solde;
	
	private String etatSoldeCompensation;

}
