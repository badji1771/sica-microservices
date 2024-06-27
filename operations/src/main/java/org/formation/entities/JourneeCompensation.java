package org.formation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JourneeCompensation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateJournee;
    
    private String libelle;

    private Date heureFermeture;

    private String etat;

    @ManyToOne
    private TypeCompensation typeCompensation;
}
