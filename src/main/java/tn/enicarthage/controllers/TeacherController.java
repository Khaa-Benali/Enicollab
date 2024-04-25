package tn.enicarthage.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.dto.MatiereDto;
import tn.enicarthage.dto.ProjetDto;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.services.teacher.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {


	@Autowired
    private TeacherService teacherService;

    @PostMapping("/addProjetToMatiere")
    public ResponseEntity<Matiere> addProjetToMatiere(@RequestParam Long idMatiere, @RequestBody Projet projet) {
        Matiere updatedMatiere = teacherService.addProjetToMatiere(idMatiere, projet);
        return new ResponseEntity<>(updatedMatiere, HttpStatus.OK);
    }

    @PostMapping("/assignTeamToProjet")
    public ResponseEntity<Void> assignTeamToProjet(@RequestParam Long idProjet, @RequestBody List<String> emails) {
        teacherService.assignTeamToProjet(idProjet, emails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/projet/{id}/tache")
    public ResponseEntity<Tache> addTacheToProjet(@PathVariable("id") Long projetId, @RequestBody Tache tache) {
        Tache addedTache = teacherService.addTacheToProjet(projetId, tache);
        return new ResponseEntity<>(addedTache, HttpStatus.CREATED);
    }
    @PostMapping("/tache/{id}/note")
    public ResponseEntity<String> noterTache(@PathVariable("id") Long tacheId, @RequestBody double note) {
        try {
            teacherService.noterTache(tacheId, note);
            return ResponseEntity.ok("La tâche a été notée avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la notation de la tâche.");
        }
    }
    @GetMapping("/projet/{id}/note-finale")
    public ResponseEntity<Double> calculNoteFinalProjet(@PathVariable("id") Long projetId) {
        try {
            double noteFinale = teacherService.calculNoteFinal(projetId);
            return ResponseEntity.ok(noteFinale);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/projects")
    public ResponseEntity<List<ProjetDto>> getAllProjetsTeacher() {
        List<ProjetDto> projets = teacherService.getAllProjects();
        return ResponseEntity.ok(projets);
    }
    @GetMapping("/matieres")
    public ResponseEntity<List<MatiereDto>> getAllMatieresProf() {
        List<MatiereDto> matieres = teacherService.getAllMatieres();
        return ResponseEntity.ok(matieres);
    }

}