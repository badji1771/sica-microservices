package org.formation.sica.gatewaysica.feign;

import org.formation.sica.gatewaysica.entity.DetailsReglement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="reglement-detail",url = "http://localhost:8092",path = "/api/details")
public interface DetailsReglementClient {
    @GetMapping
    public List<DetailsReglement> loadAll();

    @GetMapping("{id}")
    public List<DetailsReglement> getDetailsReglementById(@PathVariable("id") Long id);
}
