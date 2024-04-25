package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.enicarthage.EnicollabApplication;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.repositories.ProjetRepository;


@SpringBootTest(classes = EnicollabApplication.class)
public class ProjetRepositoryTests {

    @Autowired
    private ProjetRepository projetRepository;

    @Test
    public void saveProjetTest() {
        Projet projet1 = new Projet();
        projet1.setNom("Projet 1");
        projetRepository.save(projet1);
        Assertions.assertTrue(projet1.getId() != null && projet1.getId() > 0);
    }
    @Test
    public void getProjetByIdTest() {
       
        Long projectId = 6L;
        Projet ReturnProjet = projetRepository.getProjetById(projectId);
        assertThat(ReturnProjet).isNotNull();
        assertThat(ReturnProjet.getId()).isEqualTo(projectId);
    }
 
}
