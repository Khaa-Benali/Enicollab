package tn.enicarthage.service;
import java.util.List;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Projet;

public interface MatiereService {
    List<Matiere> getAllMatieres();
    Matiere getMatiereById(Long id);
    Matiere saveMatiere(Matiere matiere);
    void deleteMatiere(Long id);
	Matiere addProjetToMatiere(Long id, Projet projet);
}
