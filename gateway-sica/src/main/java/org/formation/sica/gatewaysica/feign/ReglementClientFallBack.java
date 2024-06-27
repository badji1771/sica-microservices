package org.formation.sica.gatewaysica.feign;

import org.formation.sica.gatewaysica.entity.DetailsReglement;
import org.formation.sica.gatewaysica.entity.Reglement;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ReglementClientFallBack implements ReglementClient{

    @Override
    public List<Reglement> loadAll() {
        return List.of();
    }

    @Override
    public Reglement getReglementById(Long id) {
        return new Reglement();
    }

    @Override
    public List<DetailsReglement> getDetailsReglementById(Long id) {
        return List.of();
    }
}


