package org.formation.controller;


import org.formation.service.dto.operationrejet.OperationRejetRequest;
import org.formation.service.dto.operationrejet.OperationRejetResponse;
import org.formation.service.operationrejet.OperationRejetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/operation-rejets")
public class OperationRejetController {

    private final OperationRejetService operationRejetService;

    public OperationRejetController(OperationRejetService operationRejetService) {
        this.operationRejetService = operationRejetService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OperationRejetResponse create(@RequestBody OperationRejetRequest request){
        return operationRejetService.create(request);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public OperationRejetResponse update(@RequestBody OperationRejetRequest request){
        return operationRejetService.update(request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        operationRejetService.delete(id);
    }

    @PostMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public List<OperationRejetResponse> getOperationRejet(@RequestBody OperationRejetRequest criteria){
        return operationRejetService.getOperationRejet(criteria);
    }
}
