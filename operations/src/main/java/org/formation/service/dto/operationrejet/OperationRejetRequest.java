package org.formation.service.dto.operationrejet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationRejetRequest {
    private Long id;
    private String rejet;

    private String typeOperation;
}
