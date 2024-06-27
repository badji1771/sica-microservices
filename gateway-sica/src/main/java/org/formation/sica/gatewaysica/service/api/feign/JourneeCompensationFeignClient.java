package org.formation.sica.gatewaysica.service.api.feign;

import org.formation.sica.gatewaysica.dto.JourneeCompensationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="operation",path = "api/journee-compensations", fallback = JourneeCompensationFeignClientFallBack.class)
public interface JourneeCompensationFeignClient {
    @GetMapping("{id}")
    ResponseEntity<JourneeCompensationResponse> getJourneeById(@PathVariable("id") Long id);

    @PutMapping("{id}/ouvrir-journee")
    ResponseEntity<JourneeCompensationResponse> ouvrirJournee(@PathVariable("id") Long id);

    @PutMapping("{id}/fermer-journee")
    ResponseEntity<JourneeCompensationResponse> fermerJournee(@PathVariable("id") Long id);

    @GetMapping("/journee")
    ResponseEntity<JourneeCompensationResponse> listerJournee();

}
