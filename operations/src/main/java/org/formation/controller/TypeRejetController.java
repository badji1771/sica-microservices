package org.formation.controller;


import org.formation.service.dto.typerejet.CriteriaTypeRejet;
import org.formation.service.dto.typerejet.TypeRejetRequest;
import org.formation.service.dto.typerejet.TypeRejetResponse;
import org.formation.service.typerejet.TyperejetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/type-rejets")
public class TypeRejetController {

    private final TyperejetService typerejetService;

    public TypeRejetController(TyperejetService typerejetService) {
        this.typerejetService = typerejetService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TypeRejetResponse create(@RequestBody TypeRejetRequest request){
        return typerejetService.create(request);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public TypeRejetResponse update(@RequestBody TypeRejetRequest request){
        return typerejetService.update(request);
    }

    @DeleteMapping(path = "/{code}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String code){
        typerejetService.delete(code);
    }

    @PostMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TypeRejetResponse>  getTypeRejet(@RequestBody CriteriaTypeRejet criteria){
        return typerejetService.getTypeRejet(criteria);
    }

}
