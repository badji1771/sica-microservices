package org.formation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.util.Etat;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteReglementDto {

    private String reference;

    private String libelle;


    private BigDecimal solde;

    private Etat etatCompte;

    private BanqueDto banque;

}
