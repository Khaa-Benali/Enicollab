package tn.enicarthage.services.depots;

import java.util.List;

import tn.enicarthage.entities.Depot;

public interface DepotService {

	List<Depot> getAllDepots();

	Depot getDepotById(Long id);

	Depot createDepot(Depot depot);

	void deleteDepot(Long id);
 
}
