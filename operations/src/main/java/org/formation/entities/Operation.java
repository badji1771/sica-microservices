package org.formation.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import org.formation.utils.Utulitaires;
import org.formation.utils.Utulitaires.ETAT;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String reference;

    @ManyToOne
    @NotNull
    private TypeOperation typeOperation;

    @ManyToOne
    private JourneeCompensation journeeCompensation;

    @NotNull
    private String codeBanqueSource;

    @NotNull
    private String codeBanqueDestination;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "banque_source_code")),
            @AttributeOverride(name = "libelle", column = @Column(name = "banque_source_libelle")),
            @AttributeOverride(name = "abrege", column = @Column(name = "banque_source_abrege")),
            @AttributeOverride(name = "compteReglement", column = @Column(name = "banque_source_compte_reglement")),
            @AttributeOverride(name = "etat", column = @Column(name = "banque_source_etat"))
    })
    private Banque banqueSource;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "banque_destination_code")),
            @AttributeOverride(name = "libelle", column = @Column(name = "banque_destination_libelle")),
            @AttributeOverride(name = "abrege", column = @Column(name = "banque_destination_abrege")),
            @AttributeOverride(name = "compteReglement", column = @Column(name = "banque_destination_compte_reglement")),
            @AttributeOverride(name = "etat", column = @Column(name = "banque_destination_etat"))
    })
    private Banque banqueDestination;

    @ManyToOne
    private TypeRejet typeRejet;

    private String formuleNumerote;

    @NotNull
    private BigDecimal montant;

    private Date dateReglement;

    @NotNull
    private Date dateCreation;

    @NotNull
    private Date dateModifictaion;

    @NotNull
    private ETAT etat;
    //= Utulitaires.ETAT.ACCEPTE;


}
