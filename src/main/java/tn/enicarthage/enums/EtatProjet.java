package tn.enicarthage.enums;

public enum EtatProjet {
    EN_COURS("En cours"),
    TERMINE("Terminé"),
    ANNULE("Annulé");

    private final String value;

    EtatProjet(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
