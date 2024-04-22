package tn.enicarthage.service;

import tn.enicarthage.entities.Projet;

import java.util.List;

public interface ProjetService {
 List<Projet> getAllProjets();

 Projet getProjetById(Long id);

 Projet saveProjet(Projet projet);

 void deleteProjet(Long id);
}

