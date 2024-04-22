package tn.enicarthage.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import tn.enicarthage.enums.EtatProjet;

@Entity
@Data
@Table(name = "projets")
public class Projet {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nom;
	    private String description ;
	    private Date dateD;
	    private Date dateF;
	    private double note;
	    public double getNote() {
			return note;
		}

		public void setNote(double note) {
			this.note = note;
		}
		@Enumerated(EnumType.STRING)
	    private EtatProjet etatProjet;
	    public String getEtatProjetAsString() {
	        return etatProjet.getValue();
	    
	    }
	    @ManyToOne
	    @JoinColumn(name = "matiere_id")
	    private Matiere matiere;
	    
	    @OneToMany(mappedBy = "projet")
	    private List<Tache> taches;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "projet_student",
	        joinColumns = @JoinColumn(name = "projet_id"),
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private List<User> team;
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

		public EtatProjet getEtatProjet() {
			return etatProjet;
		}

		public void setEtatProjet(EtatProjet etatProjet) {
			this.etatProjet = etatProjet;
		}

		public Matiere getMatiere() {
			return matiere;
		}

		public void setMatiere(Matiere matiere) {
			this.matiere = matiere;
		}

		public List<Tache> getTaches() {
			return taches;
		}

		public void setTaches(List<Tache> taches) {
			this.taches = taches;
		}

		public List<User> getTeam() {
			return team;
		}

		public void setTeam(List<User> team) {
			this.team = team;
		}
	    

}
