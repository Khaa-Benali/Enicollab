package tn.enicarthage.services.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import tn.enicarthage.dto.StudentDto;
import tn.enicarthage.dto.TeacherDto;
import tn.enicarthage.entities.Teacher;
import tn.enicarthage.entities.User;
import tn.enicarthage.enums.UserRole;
import tn.enicarthage.repositories.TeacherRepository;
import tn.enicarthage.repositories.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	public AdminServiceImpl(UserRepository userRepository, TeacherRepository teacherRepository) {
		this.userRepository = userRepository;
		this.teacherRepository = teacherRepository;
	}

	private final UserRepository userRepository;
	private final TeacherRepository teacherRepository;

	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(adminAccount == null) {
		User admin = new User();
		admin.setEmail("admin@test.com");
		admin.setName("admin");
		admin.setRole(UserRole.ADMIN);
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
	    userRepository.save(admin);
		}
	}

	@Override
	public StudentDto postStudent(StudentDto studentDto) {
		Optional<User> optionalUser = userRepository.findFirstByEmail(studentDto.getEmail());
		if(optionalUser.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser = userRepository.save(user);
			StudentDto createdStudentDto = new StudentDto();
			createdStudentDto.setId(createdUser.getId());
			createdStudentDto.setEmail(createdUser.getEmail());
			return createdStudentDto;
			
		}
		return null;
	}
	@Override
	public List<StudentDto> getAllStudents() {
		return userRepository.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto).collect(Collectors.toList());
	}

	@Override
	public void deleteStudent(Long studentId) {
		userRepository.deleteById(studentId);
		
	}
	@Override
	public TeacherDto postTeacher(TeacherDto teacherDto) {
	    Teacher teacher = new Teacher();
	    BeanUtils.copyProperties(teacherDto, teacher);
	    
	    // Hasher le mot de passe
	    String hashedPassword = new BCryptPasswordEncoder().encode(teacherDto.getPassword());
	    teacher.setPassword(hashedPassword);
	    
	    Teacher createdTeacher = teacherRepository.save(teacher);
	    
	    TeacherDto createdTeacherDto = new TeacherDto();
	    createdTeacherDto.setId(createdTeacher.getId());
	    
	    return createdTeacherDto;
	}


	@Override
	public List<TeacherDto> getAllTeachers() {
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
	}

	@Override
	public void deleteTeacher(Long teacherId) {
		teacherRepository.deleteById(teacherId);
		
	}

}
