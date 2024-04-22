package tn.enicarthage.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Niveau;
import tn.enicarthage.services.niveaux.NiveauService;

import java.util.List;

@RestController
@RequestMapping("/niveaux")
public class NiveauController {

    @Autowired
    private NiveauService niveauService;

    @GetMapping
    public ResponseEntity<List<Niveau>> getAllNiveaux() {
        List<Niveau> niveaux = niveauService.getAllNiveaux();
        return ResponseEntity.ok(niveaux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Niveau> getNiveauById(@PathVariable Long id) {
        Niveau niveau = niveauService.getNiveauById(id);
        return ResponseEntity.ok(niveau);
    }

    @PostMapping("/niveau")
    public ResponseEntity<Niveau> createNiveau(@RequestBody Niveau niveau) {
        Niveau createdNiveau = niveauService.createNiveau(niveau);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNiveau);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Niveau> updateNiveau(@PathVariable Long id, @RequestBody Niveau newNiveauInfo) {
        Niveau updatedNiveau = niveauService.updateNiveau(id, newNiveauInfo);
        return ResponseEntity.ok(updatedNiveau);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNiveau(@PathVariable Long id) {
        niveauService.deleteNiveau(id);
        return ResponseEntity.noContent().build();
    }
}
