package org.formation.sica.gatewaysica.dto;

import java.util.Date;

public class JourneeCompensationResponse {
    private Long id;
    private Date dateJournee;
    private String libelle;
    private Date heureFermeture;
    private String etat;
    private String typeCompensationCode;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Date getDateJournee() {
        return dateJournee;
    }
    public void setDateJournee(Date dateJournee) {
        this.dateJournee = dateJournee;
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
    public String getTypeCompensationCode() {
        return typeCompensationCode;
    }
    public void setTypeCompensationCode(String typeCompensationCode) {
        this.typeCompensationCode = typeCompensationCode;
    }

    @Override
    public String toString() {
        return "JourneeCompensationResponse{" +
                "id=" + id +
                ", dateJournee=" + dateJournee +
                ", libelle='" + libelle + '\'' +
                ", heureFermeture=" + heureFermeture +
                ", etat='" + etat + '\'' +
                ", typeCompensationCode='" + typeCompensationCode + '\'' +
                '}';
    }
}
