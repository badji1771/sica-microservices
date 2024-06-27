package org.formation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationDto {


    private Long  id ;

    private String reference;


    private String typeOperation ;


    private Integer journeeCompensation ;


    private String banqueSource ;


    private String banqueDestination ;


    private String typeRejet ;

    private String formuleNumerote;

    private BigDecimal montant ;

    private Date dateReglement ;

    private Date dateCreation ;

    private Date dateModifictaion ;

    private String etat; // REJETE PAYE
}
