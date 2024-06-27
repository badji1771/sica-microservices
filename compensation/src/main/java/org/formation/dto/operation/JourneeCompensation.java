package org.formation.dto.operation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JourneeCompensation {
    private Long id;

    private Date dateJournee;
    
    private String libelle;

    private Date heureFermeture;

    private String etat;


    private TypeCompensation typeCompensation;
}
