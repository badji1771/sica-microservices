package org.formation.controller;

import java.util.List;

import org.formation.entities.Operation;
import org.formation.mappers.OperationMapper;
import org.formation.service.BanqueService;
import org.formation.service.OperationService;
import org.formation.service.dto.operation.OperationDto;
import org.formation.service.dto.operation.OperationRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
public class OperationControlleur {


    private final OperationService operationService;
    private final OperationMapper operationMapper;

    public OperationControlleur(OperationService operationService, OperationMapper operationMapper, BanqueService banqueService) {
        this.operationService = operationService;
        this.operationMapper = operationMapper;

    }



    @GetMapping
    public List<Operation> getAllOperation() {
        return operationService.getAllOperation();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public OperationDto findById(@PathVariable Long id) {
        return operationService.findById(id);

    }

    @PutMapping("rejet/{id}")
    public OperationDto rejetOperation(@PathVariable Long id, @RequestParam String codeRejet) {
        return operationService.rejetOperation(id, codeRejet);
    }

    @GetMapping("journee/{idCompense}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<OperationDto> getOperationsParJourneeCompense(@PathVariable Long idCompense, @RequestParam(required = false) OperationRequestDTO operationRequestDTO) {
        return operationMapper.OperationToOperationDto(operationService.getOperationsParJourneeCompense(idCompense, operationRequestDTO));
    }

}