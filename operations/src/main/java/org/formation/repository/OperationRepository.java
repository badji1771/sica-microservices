package org.formation.repository;

import java.util.List;

import org.formation.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_id = ?1 and o.banque_source_code = ?2 and o.type_operation_code = ?3 ", nativeQuery = true)
	List<Operation> getOperationsParJourneeCompenseBanqueSourceTypeOperation(Long idCompense, String codeBanque, String typeOpeartion );
	
	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_id = ?1 and o.banque_source_code = ?2 and o.type_operation_code = ?3 ", nativeQuery = true)
	List<Operation> getOperationsParJourneeCompenseBanqueDestinationTypeOperation(Long idCompense, String codeBanque, String typeOpeartion );
	
	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_id = ?1 and o.banque_source_code = ?2 ", nativeQuery = true)
	List<Operation> getOperationsParJourneeCompenseBanqueSource(Long idCompense, String codeBanque );
	
	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_id = ?1 and o.banque_destination_code = ?2 ", nativeQuery = true)
	List<Operation> getOperationsParJourneeCompenseBanqueDestination(Long idCompense, String codeBanque );
	
	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_id = ?1 and o.type_operation_code = ?2 ", nativeQuery = true)
	List<Operation> getOperationsParJourneeCompenseTypeOperation(Long idCompense, String typeOperation_code );
	
	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_id = ?1", nativeQuery = true)
	List<Operation> getOperationsParJourneeCompense(Long idCompense);
	
	
	
//
//	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_date_journee.dateJournee = ?1 and o.journee_compensation_heure_fermeture  = ?2 and code_banque = ?3", nativeQuery = true)
//	List<Operation> getOperationsParBanquePeriode(PeriodeCompensationxx periodeCompensation, String codeBanque);
//
//	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_date_journee = ?1 and o.journee_compensation_heure_fermeture = ?2", nativeQuery = true)
//	List<Operation> getOperationsParPeriode(PeriodeCompensationxx periodeCompensation);
	
//	@Query( value = "SELECT * FROM Operation o WHERE o.journee_compensation_date_journee = ?1 and o.journee_compensation_heure_fermeture  = ?2 and code_banque = ?3", nativeQuery = true)
//	List<Operation> getOperationsParTypeOperationPeriode(PeriodeCompensationxx periodeCompensation, String typeOperation);
//

}

