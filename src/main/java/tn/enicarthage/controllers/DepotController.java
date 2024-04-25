package tn.enicarthage.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.enicarthage.entities.Depot;
import tn.enicarthage.services.depots.DepotService;

import java.util.List;

@RestController
@RequestMapping("/depots")
public class DepotController {

    private static final Logger logger = LoggerFactory.getLogger(DepotController.class);

    @Autowired
    private DepotService depotService;

    @GetMapping
    public ResponseEntity<List<Depot>> getAllDepots() {
        logger.info("Récupération de tous les dépôts");
        List<Depot> depots = depotService.getAllDepots();
        return ResponseEntity.ok(depots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Depot> getDepotById(@PathVariable Long id) {
        logger.info("Récupération du dépôt avec l'ID : {}", id);
        Depot depot = depotService.getDepotById(id);
        return ResponseEntity.ok(depot);
    }

    @PostMapping
    public ResponseEntity<Depot> createDepot(@RequestBody Depot depot) {
        logger.info("Création d'un nouveau dépôt : {}", depot);
        Depot createdDepot = depotService.createDepot(depot);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepot(@PathVariable Long id) {
        logger.info("Suppression du dépôt avec l'ID : {}", id);
        depotService.deleteDepot(id);
        return ResponseEntity.noContent().build();
    }
}
