package com.company.pays.repository;

import com.company.pays.entity.JourFerier;
import io.jmix.core.repository.JmixDataRepository;
import io.jmix.core.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JourFerierRepository extends JmixDataRepository<JourFerier, UUID> {
        @Query("SELECT f FROM JourFerier f LEFT JOIN f.calendrier c LEFT JOIN c.pays  p WHERE p.id=:paysId AND f.dateJourFerier=:dateJour")
        Optional<List<JourFerier>> findAllJourFerierByPaysAndDate(@Param("paysId") UUID paysId, @Param("dateJour") LocalDate dateJour);
}