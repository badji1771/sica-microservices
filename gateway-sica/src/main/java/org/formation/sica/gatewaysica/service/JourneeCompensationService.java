package org.formation.sica.gatewaysica.service;

import io.jmix.core.DataManager;
import io.jmix.core.EntityStates;
import org.formation.sica.gatewaysica.dto.JourneeCompensationResponse;
import org.formation.sica.gatewaysica.entity.JourneeCompensation;
import org.formation.sica.gatewaysica.service.api.IJourneeCompensationApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JourneeCompensationService {
    private final IJourneeCompensationApiService journeeCompensationApiService;
    private final EntityStates entityStates;

    @Autowired
    protected DataManager dataManager;

    public JourneeCompensationService(EntityStates entityStates, IJourneeCompensationApiService journeeCompensationApiService) {
        this.entityStates = entityStates;
        this.journeeCompensationApiService = journeeCompensationApiService;
    }

    private void setState(JourneeCompensation... journeeCompensations) {
        for (JourneeCompensation journeeCompensation : journeeCompensations) {
            entityStates.setNew(journeeCompensation, false);
        }
    }

    public List<JourneeCompensation> loadAll() {
        List<JourneeCompensation> result = Collections.singletonList(buildJourneeCompensation(this.journeeCompensationApiService.getJourneCompensationById(1L)));
        setState(result.toArray(new JourneeCompensation[0]));
        return result;
    }

    public JourneeCompensation loadJourneeCompensation(Long id) {
        JourneeCompensation result = buildJourneeCompensation(this.journeeCompensationApiService.getJourneCompensationById(id));
        setState(result);
        return result;
    }

    JourneeCompensation buildJourneeCompensation(JourneeCompensationResponse apiResponse){
        if(apiResponse == null){
            return null;
        }
        JourneeCompensation result = dataManager.create(JourneeCompensation.class);
        result.setLibelle(apiResponse.getLibelle());
        result.setDateJournee(apiResponse.getDateJournee());
        result.setHeureFermeture(apiResponse.getHeureFermeture());
        result.setEtat(apiResponse.getEtat());
        result.setTypeCompensationCode(apiResponse.getTypeCompensationCode());
        return result;
    }
}
