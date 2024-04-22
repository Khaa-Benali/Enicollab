package tn.enicarthage.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enicarthage.entities.Tache;

public interface TacheRepository extends JpaRepository<Tache,Long>{

	List<Tache> findByProjetId(Long projetId);

	Tache findTacheById(Long tacheId);
	

    
}
