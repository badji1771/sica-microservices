package com.company.pays.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PAYS", uniqueConstraints = {
        @UniqueConstraint(name = "IDX_PAYS_UNQ_CODE_ISO", columnNames = {"CODE_ISO"}),
        @UniqueConstraint(name = "IDX_PAYS_UNQ_LIBELLE", columnNames = {"LIBELLE"})
})
@Entity
public class Pays {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @Column(name = "CODE_ISO", nullable = false)
    private String codeIso;

    @Column(name = "LIBELLE", nullable = false)
    @NotNull
    private String libelle;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    @JoinTable(name = "CALENDRIER_PAYS_LINK",
            joinColumns = @JoinColumn(name = "PAYS_ID"),
            inverseJoinColumns = @JoinColumn(name = "CALENDRIER_ID"))
    @OrderBy("libelle ASC")
    @ManyToMany
    private List<Calendrier> calendriers;

    public List<Calendrier> getCalendriers() {
        return calendriers;
    }

    public void setCalendriers(List<Calendrier> calendriers) {
        this.calendriers = calendriers;
    }

    public String getCodeIso() {
        return codeIso;
    }

    public void setCodeIso(String code_iso) {
        this.codeIso = code_iso;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}