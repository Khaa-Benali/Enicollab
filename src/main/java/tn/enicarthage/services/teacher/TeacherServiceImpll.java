package tn.enicarthage.services.teacher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Teacher;
import tn.enicarthage.repositories.TeacherRepository;

@Service
public class TeacherServiceImpll implements TeacherService{
	@Autowired
	private TeacherRepository teacherRepository;
	@Override
    public Optional<Teacher> findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }


}
