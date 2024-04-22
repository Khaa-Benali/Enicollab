package tn.enicarthage.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import tn.enicarthage.enums.Role;

import java.sql.Date;


import java.util.List;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String password;
    private Role role;
    
}

