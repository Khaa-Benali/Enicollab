package tn.enicarthage.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.services.matieres.MatiereService;

import java.util.List;

@RestController
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping("/")
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        List<Matiere> matieres = matiereService.getAllMatieres();
        return ResponseEntity.ok(matieres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id) {
        Matiere matiere = matiereService.getMatiereById(id);
        if (matiere != null) {
            return ResponseEntity.ok(matiere);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/matiere")
    public ResponseEntity<Matiere> saveMatiere(@RequestBody Matiere matiere) {
        Matiere savedMatiere = matiereService.saveMatiere(matiere);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMatiere);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/addprojets")
    public ResponseEntity<Matiere> addProjetToMatiere (@PathVariable Long id, @RequestBody Projet projet) {
        Matiere matiere = matiereService.addProjetToMatiere(id, projet);
        return new ResponseEntity<>(matiere, HttpStatus.OK);
    }
}

