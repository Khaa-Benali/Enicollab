package tn.enicarthage.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.entities.Tache;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
	List<Tache> findByAssignationUserId(Long userId);
    List<Tache> findByDateEcheanceBeforeAndAssignationUserNotNull(Date date);
}
