package org.formation.service.dto.operation;

import org.formation.utils.Utulitaires.SENS_BANQUE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequestDTO {

	private String typeOperation;
	
	private String codeBanque;
	
	private SENS_BANQUE sensBanque;
	
}
