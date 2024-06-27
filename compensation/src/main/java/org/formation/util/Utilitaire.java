package org.formation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilitaire {

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


    public static String dateToStringSql(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String sDate = dateFormat.format(date);
        String jour = sDate.substring(0,2);
        String mois = sDate.substring(3,5);
        String annee = sDate.substring(6,10);
        sDate=annee+"-"+mois+"-"+jour;
        return sDate;
    }
}
