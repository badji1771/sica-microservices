package org.formation.domain;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="SOLDE_COMPENSATION")
@Builder
public class SoldeCompensation {
	
	@Id
	@Column(name="NU_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NU_JOURNEECOMPENSATION_FK")
	private Long nuJourneeCompense;

	@Column(name="DA_DATEOPERATION")
	private Date dateCompense;

	@Column(name="CH_BANQUE_FK")
	private String codeBanque;

	@Column(name="NU_SOLDE")
	private BigDecimal soldeCompense;

	@Column(name="CH_ETAT")
	private String etat;

	@Column(name="TYPE_OPERATION")
	private String typeOperation;
	
	

}
