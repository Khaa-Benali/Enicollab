package tn.enicarthage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

	Optional<Teacher> findByEmail(String email);

	

	
}
