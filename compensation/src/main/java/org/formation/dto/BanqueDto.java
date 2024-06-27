package org.formation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.util.Etat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BanqueDto {

    private String codeBanque;
    private String libelle;
    private Etat etatBanque;
    private CompteReglementDto compte;
    private PaysDto pays;


}
