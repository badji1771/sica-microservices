package org.formation.sica.gatewaysica.entity;

import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.Store;

import java.util.Date;

@Store(name = "journeeCompensationds")
@JmixEntity
public class JourneeCompensation {
    @JmixId
    private Long id;
    private Date dateJournee;
    private String libelle;
    private Date heureFermeture;
    private String etat;
    private String typeCompensationCode;

    public Date getDateJournee() {
        return dateJournee;
    }
    public void setDateJournee(Date dateJournee) {
        this.dateJournee = dateJournee;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public Date getHeureFermeture() {
        return heureFermeture;
    }
    public void setHeureFermeture(Date heureFermeture) {
        this.heureFermeture = heureFermeture;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTypeCompensationCode() {
        return typeCompensationCode;
    }
    public void setTypeCompensationCode(String typeCompensationCode) {
        this.typeCompensationCode = typeCompensationCode;
    }
    @Override
    public String toString() {
        return "JourneeCompensation{" +
                "id=" + id +
                ", dateJournee=" + dateJournee +
                ", libelle='" + libelle + '\'' +
                ", heureFermeture=" + heureFermeture +
                ", etat='" + etat + '\'' +
                ", typeCompensationCode='" + typeCompensationCode + '\'' +
                '}';
    }
}