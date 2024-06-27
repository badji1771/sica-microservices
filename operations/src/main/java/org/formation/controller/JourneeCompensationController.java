package org.formation.controller;


import org.formation.service.dto.journeecompensation.JourCompensationRequest;
import org.formation.service.dto.journeecompensation.JourCompensationResponse;
import org.formation.service.journeecompensation.JourneeCompensationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/journee-compensations")
public class JourneeCompensationController {

    private final JourneeCompensationService compensationService;

    public JourneeCompensationController(JourneeCompensationService compensationService) {
        this.compensationService = compensationService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public JourCompensationResponse create(@RequestBody JourCompensationRequest request) {
        return compensationService.create(request);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public JourCompensationResponse update(@RequestBody JourCompensationRequest request) {
        return compensationService.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        compensationService.delete(id);
    }

    @PutMapping(path = "/{id}/ouvrir-journee")
    @ResponseStatus(code = HttpStatus.OK)
    public JourCompensationResponse ouvrirJournee(@PathVariable Long id) {
        return compensationService.ouvrirJournee(id);
    }

    @PutMapping(path = "/{id}/fermer-journee")
    @ResponseStatus(code = HttpStatus.OK)
    public JourCompensationResponse fermerJournee(@PathVariable Long id) {
        return compensationService.fermerJournee(id);
    }
}
