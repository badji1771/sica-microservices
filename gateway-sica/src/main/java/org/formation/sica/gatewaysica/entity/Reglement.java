package org.formation.sica.gatewaysica.entity;

import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.time.LocalDate;


@JmixEntity
public class Reglement {
    @JmixId
    private Long id;

    private String nomFichier;

    private LocalDate dateCreation;

    private Long idJourneeCompense;

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdJourneeCompense() {
        return idJourneeCompense;
    }

    public void setIdJourneeCompense(Long idJourneeCompense) {
        this.idJourneeCompense = idJourneeCompense;
    }
}