package org.formation.sica.gatewaysica.service.api;

import org.formation.sica.gatewaysica.dto.JourneeCompensationResponse;
import org.formation.sica.gatewaysica.service.api.feign.JourneeCompensationFeignClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class JourneeCompensationApiService implements IJourneeCompensationApiService {
    private final JourneeCompensationFeignClient journeeCompensationFeignClient;

    public JourneeCompensationApiService(@Qualifier("org.formation.sica.gatewaysica.service.api.feign.JourneeCompensationFeignClient") JourneeCompensationFeignClient journeeCompensationFeignClient ) {
        this.journeeCompensationFeignClient = journeeCompensationFeignClient;
    }

    @Override
    public JourneeCompensationResponse getJourneCompensationById(Long id) {
        return journeeCompensationFeignClient.getJourneeById(id).getBody();
    }

    @Override
    public JourneeCompensationResponse ouvrirJournee(Long id) {
        return journeeCompensationFeignClient.ouvrirJournee(id).getBody();
    }

    @Override
    public JourneeCompensationResponse fermerJournee(Long id) {
        return journeeCompensationFeignClient.fermerJournee(id).getBody();
    }
}
