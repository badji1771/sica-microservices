package org.formation.service.dto.operationrejet;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.entities.TypeOperation;
import org.formation.entities.TypeRejet;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationRejetResponse {

    private Long id;

    private TypeRejet rejet;

    private TypeOperation typeOperation;

    private String etat;
}
