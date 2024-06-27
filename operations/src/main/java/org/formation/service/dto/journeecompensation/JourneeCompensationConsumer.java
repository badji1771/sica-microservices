package org.formation.service.dto.journeecompensation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JourneeCompensationConsumer {
    private Long id;
    private String etat;
}
