package org.formation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JourneeDto {

    private Long id;
    private Date dateJournee;
    private String typeCompensation;
    private Date heureFermeture;
    private String libelle;
    private String etat;
}
