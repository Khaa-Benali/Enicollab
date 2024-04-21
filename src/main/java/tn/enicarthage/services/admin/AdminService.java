package tn.enicarthage.services.admin;

import java.util.List;

import tn.enicarthage.dto.StudentDto;
import tn.enicarthage.dto.TeacherDto;

public interface AdminService {

	StudentDto postStudent(StudentDto studentDto);

	List<StudentDto> getAllStudents();
	void deleteStudent(Long studentId);

	TeacherDto postTeacher(TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	void deleteTeacher(Long teacherId);

}
