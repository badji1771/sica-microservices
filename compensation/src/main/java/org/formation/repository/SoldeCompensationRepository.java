package org.formation.repository;

import org.formation.domain.SoldeCompensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SoldeCompensationRepository extends JpaRepository<SoldeCompensation, Long> {
    List<SoldeCompensation> findByDateCompenseAndTypeOperation(Date dateCompense, String typeOperation);
    List<SoldeCompensation> findByDateCompense(Date dateCompense);

    List<SoldeCompensation> findByDateCompenseAndTypeOperationAndCodeBanque(Date dateCompense, String typeOperation,String codeBanque);

    List<SoldeCompensation> findByDateCompenseAndCodeBanque(Date dateCompense, String codeBanque);
}
