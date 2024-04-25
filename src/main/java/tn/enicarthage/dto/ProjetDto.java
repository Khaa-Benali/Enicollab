package tn.enicarthage.dto;

import java.sql.Date;
import java.util.List;

import tn.enicarthage.enums.EtatProjet;

public class ProjetDto {
	   private Long id;
	    private String nom;
	    private String description;
	    private Date dateD;
	    private Date dateF;
	    private double note;
	    private EtatProjet etatProjet;
	    private Long matiereId;
	    private List<TacheDto> taches;
	    private List<StudentDto> team;
		public ProjetDto() {
			
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Date getDateD() {
			return dateD;
		}
		public void setDateD(Date dateD) {
			this.dateD = dateD;
		}
		public Date getDateF() {
			return dateF;
		}
		public void setDateF(Date dateF) {
			this.dateF = dateF;
		}
		public double getNote() {
			return note;
		}
		public void setNote(double note) {
			this.note = note;
		}
		public EtatProjet getEtatProjet() {
			return etatProjet;
		}
		public void setEtatProjet(EtatProjet etatProjet) {
			this.etatProjet = etatProjet;
		}
		public Long getMatiereId() {
			return matiereId;
		}
		public void setMatiereId(Long matiereId) {
			this.matiereId = matiereId;
		}
		public List<TacheDto> getTaches() {
			return taches;
		}
		public void setTaches(List<TacheDto> taches) {
			this.taches = taches;
		}
		public List<StudentDto> getTeam() {
			return team;
		}
		public void setTeam(List<StudentDto> teamIds) {
			this.team = teamIds;
		}
	
	    

}
