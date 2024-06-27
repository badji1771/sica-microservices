package org.formation.sica.gatewaysica.entity;

import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@JmixEntity
public class DetailsReglement {
   // @JmixGeneratedValue
    @JmixId
    private Long id;

    private Long journeeCompensationId;

    private Long idSoldeCompense;

    private LocalDate dateOperation;

    private Long banqueId;

    private BigDecimal solde;

    private String etatSoldeCompensation;

    private Long fichierEnteteReglement;

    public Long getFichierEnteteReglement() {
        return fichierEnteteReglement;
    }

    public void setFichierEnteteReglement(Long fichierEnteteReglement) {
        this.fichierEnteteReglement = fichierEnteteReglement;
    }

    public String getEtatSoldeCompensation() {
        return etatSoldeCompensation;
    }

    public void setEtatSoldeCompensation(String etatSoldeCompensation) {
        this.etatSoldeCompensation = etatSoldeCompensation;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public Long getBanqueId() {
        return banqueId;
    }

    public void setBanqueId(Long banqueId) {
        this.banqueId = banqueId;
    }

    public LocalDate getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Long getIdSoldeCompense() {
        return idSoldeCompense;
    }

    public void setIdSoldeCompense(Long idSoldeCompense) {
        this.idSoldeCompense = idSoldeCompense;
    }

    public Long getJourneeCompensationId() {
        return journeeCompensationId;
    }

    public void setJourneeCompensationId(Long journeeCompensationId) {
        this.journeeCompensationId = journeeCompensationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FichierDetailsReglementDTO {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    journeeCompensationId: ").append(toIndentedString(journeeCompensationId)).append("\n");
        sb.append("    idSoldeCompense: ").append(toIndentedString(idSoldeCompense)).append("\n");
        sb.append("    dateOperation: ").append(toIndentedString(dateOperation)).append("\n");
        sb.append("    banqueId: ").append(toIndentedString(banqueId)).append("\n");
        sb.append("    solde: ").append(toIndentedString(solde)).append("\n");
        sb.append("    etatSoldeCompensation: ").append(toIndentedString(etatSoldeCompensation)).append("\n");
        sb.append("    fichierEnteteReglement: ").append(toIndentedString(fichierEnteteReglement)).append("\n");
        sb.append("}");
        return sb.toString();
    }
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}