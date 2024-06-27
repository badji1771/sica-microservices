package org.formation.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JourneeCompensationDTO {

    private Long idJourneeCompense;

    private String etat;
}
