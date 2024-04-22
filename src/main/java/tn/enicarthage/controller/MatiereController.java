package tn.enicarthage.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.service.MatiereService;

@RestController
@RequestMapping("/matieres")
public class MatiereController {
    
    @Autowired
    private MatiereService matiereService;

    @GetMapping("/")
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        List<Matiere> matieres = matiereService.getAllMatieres();
        return new ResponseEntity<>(matieres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id) {
        Matiere matiere = matiereService.getMatiereById(id);
        return new ResponseEntity<>(matiere, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Matiere> saveMatiere(@RequestBody Matiere matiere) {
        Matiere savedMatiere = matiereService.saveMatiere(matiere);
        return new ResponseEntity<>(savedMatiere, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{id}/projets")
    public ResponseEntity<Matiere> addProjetToMatiere (@PathVariable Long id, @RequestBody Projet projet) {
        Matiere matiere = matiereService.addProjetToMatiere(id, projet);
        return new ResponseEntity<>(matiere, HttpStatus.OK);
    }
}

