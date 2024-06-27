package org.formation.service.operationrejet;

import org.formation.service.dto.operationrejet.OperationRejetRequest;
import org.formation.service.dto.operationrejet.OperationRejetResponse;
import java.util.List;

public interface OperationRejetService {
    OperationRejetResponse create(OperationRejetRequest request);

    OperationRejetResponse update(OperationRejetRequest request);

    void delete(Long id);

    List<OperationRejetResponse> getOperationRejet(OperationRejetRequest criteria);
}
