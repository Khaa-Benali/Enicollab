package tn.enicarthage.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import tn.enicarthage.dto.TacheDto;
@Entity
@Data
@Table(name = "taches")

public class Tache {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    
    private Date dateEcheance;
    
    private double note;
    
    private double coefficient;
    
    private boolean terminee;
    
    @ManyToOne
    @JoinColumn(name = "projet_id") 
    private Projet projet;
    
    @OneToOne(mappedBy = "tache")
    private Depot depot;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	public double getNote() {
		return note;
	}
	public void setNote(double note) {
		this.note = note;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public boolean isTerminee() {
		return terminee;
	}
	public void setTerminee(boolean terminee) {
		this.terminee = terminee;
	}
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	
 public TacheDto tacheDto() {
	 TacheDto tacheDto = new TacheDto();
	 tacheDto.setCoefficient(coefficient);
	 tacheDto.setId(id);
	 tacheDto.setDescription(description);
	 return tacheDto;
 }
}
