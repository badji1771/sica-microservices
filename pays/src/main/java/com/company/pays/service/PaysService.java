package com.company.pays.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;



@Service("pays")
public class PaysService {

    final JourFerierService jourFerierService;

    public PaysService(JourFerierService jourFerierService) {
        this.jourFerierService = jourFerierService;
    }

    public boolean isFerier(UUID paysId, LocalDate dateJour) {
        return !jourFerierService.findAllJourFerierByPaysAndDate(paysId, dateJour).isEmpty();
    }
}