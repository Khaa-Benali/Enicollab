package tn.enicarthage.controllers;


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

    @Autowired
    private TacheService tacheService;

    @GetMapping("/projet/{projetId}")
    public ResponseEntity<List<Tache>> getTachesByProjetId(@PathVariable Long projetId) {
        List<Tache> taches = tacheService.getTachesByProjetId(projetId);
        return ResponseEntity.ok(taches);
    }

    @PutMapping("/{tacheId}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long tacheId, @RequestBody Tache newTacheInfo) {
        Tache updatedTache = tacheService.updateTache(tacheId, newTacheInfo);
        return ResponseEntity.ok(updatedTache);
    }

    @PutMapping("/{tacheId}/marquerTerminee")
    public ResponseEntity<Void> marquerTacheTerminee(@PathVariable Long tacheId) {
        tacheService.marquerTacheTerminee(tacheId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tacheId}/enRetard")
    public ResponseEntity<Boolean> isTacheEnRetard(@PathVariable Long tacheId) {
        boolean enRetard = tacheService.isTacheEnRetard(tacheId);
        return ResponseEntity.ok(enRetard);
    }

    @PostMapping("/projet/{projetId}")
    public ResponseEntity<Tache> addTacheToProjet(@PathVariable Long projetId, @RequestBody Tache tache) {
        Tache addedTache = tacheService.addTacheToProjet(projetId, tache);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTache);
    }
}

