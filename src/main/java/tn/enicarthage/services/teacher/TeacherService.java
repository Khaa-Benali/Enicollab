package tn.enicarthage.services.teacher;

import java.util.Optional;

import tn.enicarthage.entities.Teacher;

public interface TeacherService {

	Optional<Teacher> findByEmail(String email);

}
