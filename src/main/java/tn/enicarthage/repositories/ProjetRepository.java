package tn.enicarthage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.User;

public interface ProjetRepository extends JpaRepository<Projet,Long> {

	Projet getProjetById(Long id);
	 List<Projet> findAll();
	List<Projet> findByTeamContaining(User user);
 
}