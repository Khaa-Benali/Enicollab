package tn.enicarthage.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import tn.enicarthage.dto.DepotDto;
@Data
@Entity
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;
    
    private Date dateSoumission;
    @OneToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateSoumission() {
		return dateSoumission;
	}

	public void setDateSoumission(Date dateSoumission) {
		this.dateSoumission = dateSoumission;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}


    public DepotDto getDepotDto() {
    	DepotDto depotDto = new DepotDto();
    	depotDto.setId(id);
    	depotDto.setContenu(contenu);
    	depotDto.setDateSoumission(null);
    	return depotDto;
    }
}
