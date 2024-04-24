package tn.enicarthage.services.teacher;

import java.util.List;
import java.util.Optional;

import tn.enicarthage.dto.ProjetDto;
import tn.enicarthage.entities.Depot;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.entities.Teacher;

public interface TeacherService {

	Optional<Teacher> findByEmail(String email);

	Projet saveProjet(Projet projet);

	Matiere addProjetToMatiere(Long id, Projet projet);
	void assignTeamToProjet(Long idProjet, List<String> emails);

	Tache addTacheToProjet(Long projetId, Tache tache);

	Depot saveDepot(Depot depot);

	void noterTache(Long tacheId, double note);
	double calculNoteFinal(Long id);

	List<Projet> getAllProjets();

	List<ProjetDto> getAllProjects();



}
