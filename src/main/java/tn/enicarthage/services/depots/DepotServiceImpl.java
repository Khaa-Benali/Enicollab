package tn.enicarthage.services.depots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.enicarthage.entities.Depot;
import tn.enicarthage.repositories.DepotRepository;

import java.util.List;

@Service
@Transactional
public class DepotServiceImpl implements DepotService {

    private final DepotRepository depotRepository;

    @Autowired
    public DepotServiceImpl(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }

    @Override
    public List<Depot> getAllDepots() {
        return depotRepository.findAll();
    }

    @Override
    public Depot getDepotById(Long id) {
        return depotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Depot not found with id: " + id));
    }

    @Override
    public Depot createDepot(Depot depot) {
        return depotRepository.save(depot);
    }

    @Override
    public void deleteDepot(Long id) {
        depotRepository.deleteById(id);
    }
}
