package tn.enicarthage.services.niveaux;

import java.util.List;

import tn.enicarthage.entities.Niveau;

public interface NiveauService {
	List<Niveau> getAllNiveaux();
	Niveau getNiveauById(Long id);
	Niveau createNiveau(Niveau niveau);
	Niveau updateNiveau(Long id, Niveau newNiveauInfo);
	 void deleteNiveau(Long id);
}
