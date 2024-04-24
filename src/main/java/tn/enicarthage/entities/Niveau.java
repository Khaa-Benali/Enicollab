package tn.enicarthage.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import tn.enicarthage.dto.NiveauDto;

@Entity
@Data
@Table(name = "Niveau")
public class Niveau {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nom;
	    

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
		
		public NiveauDto getNiveauDto() {
			NiveauDto niveauDto = new NiveauDto();
			niveauDto.setId(id);
			niveauDto.setNom(nom);
			return niveauDto;
			
		}

}
