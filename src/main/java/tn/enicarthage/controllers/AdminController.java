package tn.enicarthage.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.dto.StudentDto;
import tn.enicarthage.dto.TeacherDto;
import tn.enicarthage.services.admin.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping("/student")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto) {
		StudentDto createdStudentDto = adminService.postStudent(studentDto);
		if (createdStudentDto == null) 
			return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentDto);
		
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		List<StudentDto> allStudents = adminService.getAllStudents();
	     return ResponseEntity.ok(allStudents);
	}
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Void> deleteStudent (@PathVariable Long studentId) {
	    adminService.deleteStudent(studentId);
	    return ResponseEntity.noContent().build();
	}
	
	//Teachers Operations 
	
	@PostMapping("/teacher")
	public ResponseEntity<?> postTeacher(@RequestBody TeacherDto teacherDto) {
		TeacherDto createdTeacherDto = adminService.postTeacher(teacherDto);
		if (createdTeacherDto == null) 
			return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacherDto);
		
	}
	 
	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers(){
	    List<TeacherDto> allTeachers = adminService.getAllTeachers();
	    return ResponseEntity.ok(allTeachers);
	}

	@DeleteMapping("/teacher/{teacherId}")
	public ResponseEntity<Void> deleteTeacher (@PathVariable Long teacherId) {
	    adminService.deleteTeacher(teacherId);
	    return ResponseEntity.noContent().build();
	}



}
