package tn.enicarthage.dto;

import java.sql.Date;

public class DepotDto {
	  private Long id;
	    private String contenu;
	    private Date dateSoumission;
	   
	    // Getters and setters
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


}
