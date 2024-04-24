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

import jakarta.persistence.EntityNotFoundException;
import tn.enicarthage.dto.ProjetDto;
import tn.enicarthage.services.student.StudentService;

@RestController
@RequestMapping("/api/Student")
public class StudentController {
	@Autowired
    private StudentService studentService;
	
	@GetMapping("/{userId}/projets")
    public ResponseEntity<List<ProjetDto>> getProjetsByUserId(@PathVariable Long userId) {
        List<ProjetDto> projets = studentService.getProjetsByUserId(userId);
        return ResponseEntity.ok(projets);
    }
	
	@PostMapping("/deposer-travail")
    public ResponseEntity<String> deposerTravail(
            @RequestParam Long userId,
            @RequestParam Long tacheId,
            @RequestBody String contenu) {
        try {
            studentService.deposerTravail(userId, tacheId, contenu);
            return ResponseEntity.ok("Travail déposé avec succès.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
