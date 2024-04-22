package tn.enicarthage.controllers;

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

    @Autowired
    private DepotService depotService;

    @GetMapping
    public ResponseEntity<List<Depot>> getAllDepots() {
        List<Depot> depots = depotService.getAllDepots();
        return ResponseEntity.ok(depots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Depot> getDepotById(@PathVariable Long id) {
        Depot depot = depotService.getDepotById(id);
        return ResponseEntity.ok(depot);
    }

    @PostMapping
    public ResponseEntity<Depot> createDepot(@RequestBody Depot depot) {
        Depot createdDepot = depotService.createDepot(depot);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepot(@PathVariable Long id) {
        depotService.deleteDepot(id);
        return ResponseEntity.noContent().build();
    }
}
