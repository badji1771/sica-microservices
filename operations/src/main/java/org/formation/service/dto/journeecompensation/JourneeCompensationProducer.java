package org.formation.service.dto.journeecompensation;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class JourneeCompensationProducer {

    private Long id;

    private Date dateJournee;

    private String typeCompensation;

    private String etat;
}
