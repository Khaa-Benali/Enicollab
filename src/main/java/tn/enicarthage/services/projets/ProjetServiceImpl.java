package tn.enicarthage.services.projets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.User;
import tn.enicarthage.enums.UserRole;
import tn.enicarthage.repositories.ProjetRepository;
import tn.enicarthage.repositories.UserRepository;

@Service
public class ProjetServiceImpl implements ProjetService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private ProjetRepository projetRepository;

	 
	 @Override
	 public Projet saveProjet(Projet projet) {
	     return projetRepository.save(projet);
	 }
	 @Override
	 public Projet getProjetById(Long id) {
	     return projetRepository.findById(id).orElse(null);
	 }

	 @Override
	 public void deleteProjet(Long id) {
	     projetRepository.deleteById(id);
	 }
	 @Override
	 public double CalculNoteFinal(Long id) {
		 Projet projet = projetRepository.getProjetById(id);
		 double S = 0 ;
		 for(int i = 0 ; i< projet.getTaches().size(); i++ ) {
			 S =+( projet.getTaches().get(i).getNote() * projet.getTaches().get(i).getCoefficient()) ;
			 
		 }
		 return S;
	 } 
	 @Override
	 public void assignerTeamAProjet(Long id, List<String> emails) {
	     // Créer une liste pour stocker les utilisateurs
	     List<User> list = new ArrayList<>();
	     
	     // Parcourir la liste des emails
	     for (String email : emails) {
	         // Rechercher l'utilisateur par email
	         Optional<User> userOptional = userRepository.findFirstByEmail(email);
	         if (userOptional.isPresent()) { 
	             User user = userOptional.get(); 
	             // Vérifier si l'utilisateur est un étudiant
	             if (user.getRole() == UserRole.STUDENT) {
	                 list.add(user);
	             } else {
	                 throw new IllegalArgumentException("L'utilisateur avec l'adresse e-mail " + email + " n'est pas un étudiant.");
	             }
	         } else {
	             throw new IllegalArgumentException("L'utilisateur avec l'adresse e-mail " + email + " n'existe pas.");
	         }
	     }
	     
	     // Récupérer le projet par son ID
	     Projet projet = projetRepository.findById(id)
	             .orElseThrow(() -> new IllegalArgumentException("Le projet avec l'ID " + id + " n'existe pas."));
	     
	     // Définir l'équipe du projet
	     projet.setTeam(list);
	 }
	@Override
	public List<Projet> getAllProjets() {
		return projetRepository.findAll();
	}

}
