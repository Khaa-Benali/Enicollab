package tn.enicarthage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.service.ProjetService;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {

 @Autowired
 private ProjetService projetService;

 @GetMapping("/")
 public ResponseEntity<List<Projet>> getAllProjets() {
     List<Projet> projets = projetService.getAllProjets();
     return new ResponseEntity<>(projets, HttpStatus.OK);
 }

 @GetMapping("/{id}")
 public ResponseEntity<Projet> getProjetById(@PathVariable Long id) {
     Projet projet = projetService.getProjetById(id);
     return new ResponseEntity<>(projet, HttpStatus.OK);
 }

 @PostMapping("/")
 public ResponseEntity<Projet> saveProjet(@RequestBody Projet projet) {
     Projet savedProjet = projetService.saveProjet(projet);
     return new ResponseEntity<>(savedProjet, HttpStatus.CREATED);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
     projetService.deleteProjet(id);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}
