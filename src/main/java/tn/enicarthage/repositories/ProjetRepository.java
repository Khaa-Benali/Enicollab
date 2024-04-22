package tn.enicarthage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enicarthage.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet,Long> {

	Projet getProjetById(Long id);
 
}
