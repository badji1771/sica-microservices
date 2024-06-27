package com.company.pays.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "CALENDRIER", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_CALENDRIER_UNQ_LIBELLE", columnNames = {"LIBELLE"})
})
@Entity
public class Calendrier {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "LIBELLE", nullable = false)
    @NotNull
    private String libelle;

    @OrderBy("libelle ASC")
    @ManyToMany(mappedBy = "calendriers")
    private List<Pays> pays;

    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "calendrier")
    private List<JourFerier> jourFeriers;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    public List<JourFerier> getJourFeriers() {
        return jourFeriers;
    }

    public void setJourFeriers(List<JourFerier> jourFerier) {
        this.jourFeriers = jourFerier;
    }

    public List<Pays> getPays() {
        return pays;
    }

    public void setPays(List<Pays> pays) {
        this.pays = pays;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public OffsetDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(OffsetDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}