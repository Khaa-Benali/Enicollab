package tn.enicarthage.dto;

import tn.enicarthage.entities.Niveau;
import tn.enicarthage.enums.Group;

public class StudentDto {
	   private Long id;
	   public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String name;
	   private String email;
	   private String password;
	   private Group studentClass;
	   private Niveau niveau;
	   
	   
	public Niveau getNiveau() {
		return niveau;
	}
	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Group getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(Group studentClass) {
		this.studentClass = studentClass;
	}
	   

}
