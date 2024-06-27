package org.formation.sica.gatewaysica.service.api;

import org.formation.sica.gatewaysica.dto.JourneeCompensationResponse;

public interface IJourneeCompensationApiService {
    JourneeCompensationResponse getJourneCompensationById(Long id);
    JourneeCompensationResponse ouvrirJournee(Long id);
    JourneeCompensationResponse fermerJournee(Long id);
}
