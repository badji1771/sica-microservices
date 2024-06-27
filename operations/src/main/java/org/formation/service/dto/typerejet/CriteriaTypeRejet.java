package org.formation.service.dto.typerejet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaTypeRejet {
    @Nullable
    private String code;
    @Nullable
    private String libelle;
    @Nullable
    private String type;
}
