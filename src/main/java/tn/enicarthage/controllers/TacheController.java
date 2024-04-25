package tn.enicarthage.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.services.taches.TacheService;

import java.util.List;

@RestController
@RequestMapping("/taches")
public class TacheController {

    private static final Logger logger = LoggerFactory.getLogger(TacheController.class);

    @Autowired
    private TacheService tacheService;

    @GetMapping("/projet/{projetId}")
    public ResponseEntity<List<Tache>> getTachesByProjetId(@PathVariable Long projetId) {
        logger.info("Taches récupérées pour le projet avec l'ID : {}", projetId);
        List<Tache> taches = tacheService.getTachesByProjetId(projetId);
        return ResponseEntity.ok(taches);
    }

    @PutMapping("/{tacheId}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long tacheId, @RequestBody Tache newTacheInfo) {
        logger.info("Tache mise à jour avec l'ID : {}", tacheId);
        Tache updatedTache = tacheService.updateTache(tacheId, newTacheInfo);
        return ResponseEntity.ok(updatedTache);
    }

    @PutMapping("/{tacheId}/marquerTerminee")
    public ResponseEntity<Void> marquerTacheTerminee(@PathVariable Long tacheId) {
        logger.info("Tache marquée comme terminée avec l'ID : {}", tacheId);
        tacheService.marquerTacheTerminee(tacheId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tacheId}/enRetard")
    public ResponseEntity<Boolean> isTacheEnRetard(@PathVariable Long tacheId) {
        logger.info("Vérification si la tache avec l'ID : {} est en retard", tacheId);
        boolean enRetard = tacheService.isTacheEnRetard(tacheId);
        return ResponseEntity.ok(enRetard);
    }

    @PostMapping("/projet/{projetId}")
    public ResponseEntity<Tache> addTacheToProjet(@PathVariable Long projetId, @RequestBody Tache tache) {
        logger.info("Tache ajoutée au projet avec l'ID : {}", projetId);
        Tache addedTache = tacheService.addTacheToProjet(projetId, tache);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTache);
    }
}
