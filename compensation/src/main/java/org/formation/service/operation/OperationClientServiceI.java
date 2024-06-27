package org.formation.service.operation;

import org.formation.dto.BanqueDto;
import org.formation.dto.operation.Operation;

import java.util.List;

public interface OperationClientServiceI {
    List<Operation> getAllOperationByIdJournee(Long idCompense)throws Exception;
}
