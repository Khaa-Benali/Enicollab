package tn.enicarthage.services.projets;

import java.util.List;

import tn.enicarthage.entities.Projet;

public interface ProjetService {

	 Projet getProjetById(Long id);

	 Projet saveProjet(Projet projet);

	 void deleteProjet(Long id);

	double CalculNoteFinal(Long id);

	void assignerTeamAProjet(Long id, List<String> emails);

	List<Projet> getAllProjets();

}
