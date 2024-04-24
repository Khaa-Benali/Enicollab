package tn.enicarthage.dto;

import java.sql.Date;

import tn.enicarthage.entities.Depot;

public class TacheDto {
	private Long id;
    private String description;
    private Date dateEcheance;
    private double note;
    private double coefficient;
    private boolean terminee;
    private Depot depot;
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
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
    
    

}
