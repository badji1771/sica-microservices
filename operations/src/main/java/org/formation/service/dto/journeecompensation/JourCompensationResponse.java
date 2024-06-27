package org.formation.service.dto.journeecompensation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.entities.TypeCompensation;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JourCompensationResponse {

    private Long id;

    private Date dateJournee;

    private String libelle;

    private Date heureFermeture;

    private String etat;

    private TypeCompensation typeCompensation;
}
