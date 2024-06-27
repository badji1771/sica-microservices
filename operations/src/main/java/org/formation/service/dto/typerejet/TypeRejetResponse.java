package org.formation.service.dto.typerejet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeRejetResponse {

    private String code;
    private String libelle;
    private String type;
    private String etat;
}
