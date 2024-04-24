package tn.enicarthage.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import tn.enicarthage.dto.MatiereDto;

@Entity
@Data
@Table(name = "matieres")
public class Matiere {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nom;
	    @ManyToMany(mappedBy = "matieres") 
	    private List<Teacher> teachers; 

	    // Getters and setters
	    public List<Teacher> getTeachers() {
	        return teachers;
	    }

	    public void setTeachers(List<Teacher> teachers) {
	        this.teachers = teachers;
	    }

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public List<Projet> getProjets() {
			return projets;
		}

		public void setProjets(List<Projet> projets) {
			this.projets = projets;
		}

		public Niveau getNiveau() {
			return niveau;
		}

		public void setNiveau(Niveau niveau) {
			this.niveau = niveau;
		}

		@OneToMany(mappedBy = "matiere")
	    private List<Projet> projets;
	    
	    @ManyToOne()
	    private Niveau niveau;
	    public MatiereDto getMatiereDto() {
	        MatiereDto matiereDto = new MatiereDto();
	        matiereDto.setId(this.getId());
	        matiereDto.setNom(this.getNom());

	        return matiereDto;
	    }

}
