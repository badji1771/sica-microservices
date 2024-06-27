package org.formation.utils;

public class Utulitaires {
    public enum ETAT {
        ACCEPTE,
        REJETE,
        VALIDE,
        OUVERT,
        FERME,
        ACTIVE,
        ENCOURS,
        INITIE,
        DESACTIVE
    }

    public enum SENS_BANQUE {
        S,
        D
    }
    public enum TYPE_CONPENSATION {
       NATIONALE,
       REGIONALE
    }

    private Utulitaires() {
        super();
    }

}
