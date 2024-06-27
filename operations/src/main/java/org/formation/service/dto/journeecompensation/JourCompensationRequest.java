package org.formation.service.dto.journeecompensation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JourCompensationRequest {

    private Long id;

    private Date dateJournee;

    private String libelle;

    private Date heureFermeture;

    private String typeCompensationCode;
}
