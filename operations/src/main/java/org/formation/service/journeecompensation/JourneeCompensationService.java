package org.formation.service.journeecompensation;

import org.formation.service.dto.journeecompensation.JourCompensationRequest;
import org.formation.service.dto.journeecompensation.JourCompensationResponse;
import org.formation.service.dto.journeecompensation.JourneeCompensationConsumer;

public interface JourneeCompensationService {

    JourCompensationResponse create(JourCompensationRequest request);

    JourCompensationResponse update(JourCompensationRequest request);

    void delete(Long id);

    JourCompensationResponse ouvrirJournee(Long id);

    JourCompensationResponse fermerJournee(Long id);

    void updateEtat(JourneeCompensationConsumer journeeCompensationConsumer);
}
