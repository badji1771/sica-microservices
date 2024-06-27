package org.formation.sica.gatewaysica.feign;

import org.formation.sica.gatewaysica.entity.DetailsReglement;
import org.formation.sica.gatewaysica.entity.Reglement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="reglement",url = "http://localhost:8088",path = "/api/reglements",fallback = ReglementClientFallBack.class)
public interface ReglementClient {

@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reglement> loadAll();

@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/{id}")
public Reglement getReglementById(@PathVariable("id") Long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,value = "/criteria/details/{id}")
    public List<DetailsReglement> getDetailsReglementById(@PathVariable("id") Long id);
}
