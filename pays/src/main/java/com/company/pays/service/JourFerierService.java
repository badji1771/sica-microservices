package com.company.pays.service;

import com.company.pays.entity.JourFerier;
import com.company.pays.repository.JourFerierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JourFerierService {
    final JourFerierRepository jourFerierRepository;

    public JourFerierService(JourFerierRepository jourFerierRepository) {
        this.jourFerierRepository = jourFerierRepository;
    }

    public List<JourFerier> findAllJourFerierByPaysAndDate(UUID paysId, LocalDate dateJour){
        return jourFerierRepository.findAllJourFerierByPaysAndDate(paysId, dateJour).orElse(new ArrayList<>());
    }
}