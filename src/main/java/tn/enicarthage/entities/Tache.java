package tn.enicarthage.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.util.Date;
import lombok.Data;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tache {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date dateEcheance;
    
    private String notes;
    
    private double coefficient;
    private boolean terminee;
    
    @ManyToOne
    @JoinColumn(name = "projet_id") 
    private Projet projet;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignationUser;
}
