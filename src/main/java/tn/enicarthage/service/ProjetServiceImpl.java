package tn.enicarthage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.repository.ProjetRepository;

import java.util.List;

@Service
public class ProjetServiceImpl implements ProjetService {

 @Autowired
 private ProjetRepository projetRepository;

 @Override
 public List<Projet> getAllProjets() {
     return projetRepository.findAll();
 }

 @Override
 public Projet getProjetById(Long id) {
     return projetRepository.findById(id).orElse(null);
 }

 @Override
 public Projet saveProjet(Projet projet) {
     return projetRepository.save(projet);
 }

 @Override
 public void deleteProjet(Long id) {
     projetRepository.deleteById(id);
 }
}

