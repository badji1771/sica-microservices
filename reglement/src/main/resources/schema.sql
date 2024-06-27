CREATE TABLE IF NOT EXISTS public.FichierEnteteReglement
(
    id SERIAL PRIMARY KEY,
    DA_DateCreation TIMESTAMP WITH TIME ZONE,
    CH_NomFichier character varying(255),
    ID_JOURNEE_COMPENSE BIGINT
    )
    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS public.FICHIER_DETAILS_REGLEMENT
(
    id SERIAL PRIMARY KEY,
    DA_DateOperation TIMESTAMP WITH TIME ZONE,
    NU_JourneeCompensation_FK BIGINT,
    NU_ID_SOLDE_COMPENSE BIGINT,
    CH_Banque_FK BIGINT,
    NU_Solde DECIMAL,
    CH_Etat_SOLDE_COMPENSATION character varying(255),
    NU_FichierEnteteReglement_FK BIGINT,
    CONSTRAINT fk_fichier_entete FOREIGN KEY (NU_FichierEnteteReglement_FK) REFERENCES FichierEnteteReglement(id)
    )
    TABLESPACE pg_default;