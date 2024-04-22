package tn.enicarthage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.entities.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
}
