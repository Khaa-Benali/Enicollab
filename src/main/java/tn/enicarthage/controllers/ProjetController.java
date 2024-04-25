package tn.enicarthage.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.services.projets.ProjetService;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {

    private static final Logger logger = LoggerFactory.getLogger(ProjetController.class);

    @Autowired
    private ProjetService projetService;

    @GetMapping("/")
    public ResponseEntity<List<Projet>> getAllProjets() {
        logger.info("Récupération de tous les projets");
        List<Projet> projets = projetService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    @PostMapping("/postprojet")
    public ResponseEntity<Projet> saveProjet(@RequestBody Projet projet) {
        logger.info("Enregistrement d'un nouveau projet : {}", projet);
        Projet savedProjet = projetService.saveProjet(projet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProjet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable Long id) {
        logger.info("Récupération du projet avec l'ID : {}", id);
        Projet projet = projetService.getProjetById(id);
        if (projet != null) {
            return ResponseEntity.ok(projet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        logger.info("Suppression du projet avec l'ID : {}", id);
        projetService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/calculNoteFinal")
    public ResponseEntity<Double> calculNoteFinal(@PathVariable Long id) {
        logger.info("Calcul de la note finale pour le projet avec l'ID : {}", id);
        double noteFinale = projetService.CalculNoteFinal(id);
        return ResponseEntity.ok(noteFinale);
    }

    @PostMapping("/{id}/assign-team")
    public ResponseEntity<?> assignerTeamAProjet(@PathVariable("id") Long idProjet, @RequestBody List<String> emails) {
        try {
            logger.info("Assignation d'une équipe au projet avec l'ID : {}", idProjet);
            projetService.assignerTeamAProjet(idProjet, emails);
            return ResponseEntity.ok("Équipe assignée avec succès au projet.");
        } catch (IllegalArgumentException e) {
            logger.error("Erreur lors de l'assignation de l'équipe au projet avec l'ID : {}", idProjet, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
