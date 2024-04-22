package tn.enicarthage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.enums.EtatProjet;
import tn.enicarthage.repository.MatiereRepository;
import tn.enicarthage.repository.ProjetRepository;

@Service
public class MatiereServiceImpl implements MatiereService {
    
    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private ProjetRepository projetRepository;
    
    @Override
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    @Override
    public Matiere getMatiereById(Long id) {
        return matiereRepository.findById(id).orElse(null);
    }

    @Override
    public Matiere saveMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @Override
    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }
    
    @Override
    public Matiere addProjetToMatiere(Long id, Projet projet) {
        Optional<Matiere> optionalMatiere = matiereRepository.findById(id);
        if (optionalMatiere.isPresent()) {
            Matiere matiere = optionalMatiere.get();
            projet.setMatiere(matiere); // Assigne la matière au projet
            projet.setEtatProjet(EtatProjet.EN_COURS); // Définit l'état du projet
            projetRepository.save(projet); // Enregistre le projet
            matiere.getProjets().add(projet); // Ajoute le projet à la liste des projets de la matière
            return matiereRepository.save(matiere); // Enregistre la matière mise à jour
        } else {
            throw new RuntimeException("Matiere not found with id: " + id);
        }
    }

}
