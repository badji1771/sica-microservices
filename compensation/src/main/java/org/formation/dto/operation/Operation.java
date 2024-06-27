package org.formation.dto.operation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.util.Utilitaire;
import org.formation.util.Utilitaire.ETAT;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

	private Long  id ;
	private String reference;
	private TypeOperation typeOperation ;
	private JourneeCompensation journeeCompensation ;
	private Banque banqueSource ;
	private Banque banqueDestination ;
	private TypeRejet typeRejet ;
	private String formuleNumerote;
	private BigDecimal montant ;
	private Date dateReglement ;
	private Date dateCreation ;
	private Date dateModifictaion ;
	private ETAT etat = Utilitaire.ETAT.ACCEPTE;
	
	
	

}
