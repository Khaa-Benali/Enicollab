package tn.enicarthage.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import tn.enicarthage.enums.EtatProjet;

import java.sql.Date;


import java.util.List;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Data
@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description ;
    private Date dateD;
    private Date dateF;
    @Enumerated(EnumType.STRING)
    private EtatProjet etatProjet;

    public String getEtatProjetAsString() {
        return etatProjet.getValue();
    
    }
    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;
    
    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;
    
}

