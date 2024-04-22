package tn.enicarthage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.entities.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
}
