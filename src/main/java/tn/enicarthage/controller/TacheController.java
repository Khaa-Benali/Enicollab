package tn.enicarthage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.service.TacheService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @PostMapping("/")
    public ResponseEntity<Tache> assignerTache(@RequestParam Long tacheId, @RequestParam Long userId) {
        Tache tache = tacheService.assignerTache(tacheId, userId);
        if (tache != null) {
            return new ResponseEntity<>(tache, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Tache>> getTachesByUserId(@PathVariable Long userId) {
        List<Tache> taches = tacheService.getTachesByUserId(userId);
        return new ResponseEntity<>(taches, HttpStatus.OK);
    }

    @GetMapping("/expired")
    public ResponseEntity<List<Tache>> getTachesDateEcheanceDepassee() {
        List<Tache> taches = tacheService.getTachesDateEcheanceDepassee();
        return new ResponseEntity<>(taches, HttpStatus.OK);
    }

    @GetMapping("/assignee/{tacheId}/{userId}")
    public ResponseEntity<Boolean> isTacheAssignee(@PathVariable Long tacheId, @PathVariable Long userId) {
        boolean isAssignee = tacheService.isTacheAssignee(tacheId, userId);
        return new ResponseEntity<>(isAssignee, HttpStatus.OK);
    }

    @PutMapping("/{tacheId}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long tacheId, @RequestBody Tache newTacheInfo) {
        Tache updatedTache = tacheService.updateTache(tacheId, newTacheInfo);
        if (updatedTache != null) {
            return new ResponseEntity<>(updatedTache, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{tacheId}/finish")
    public ResponseEntity<Void> marquerTacheTerminee(@PathVariable Long tacheId) {
        tacheService.marquerTacheTerminee(tacheId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> supprimerTachesUtilisateur(@PathVariable Long userId) {
        tacheService.supprimerTachesUtilisateur(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/late/{tacheId}")
    public ResponseEntity<Boolean> isTacheEnRetard(@PathVariable Long tacheId) {
        boolean isLate = tacheService.isTacheEnRetard(tacheId);
        return new ResponseEntity<>(isLate, HttpStatus.OK);
    }
}
