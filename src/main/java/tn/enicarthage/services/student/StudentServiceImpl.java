package tn.enicarthage.services.student;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tn.enicarthage.dto.ProjetDto;
import tn.enicarthage.entities.Depot;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.entities.User;
import tn.enicarthage.enums.UserRole;
import tn.enicarthage.repositories.DepotRepository;
import tn.enicarthage.repositories.ProjetRepository;
import tn.enicarthage.repositories.TacheRepository;
import tn.enicarthage.repositories.UserRepository;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired 
	private  TacheRepository tacheRepository;

	
	@Autowired 
	private DepotRepository depotRepository;

	
	@Autowired 
	private ProjetRepository projetRepository;

	@Autowired 
	private UserRepository userRepository;
	
    //consulter les projets
	@Override
	public List<ProjetDto> getProjetsByUserId(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé "));
        if (!user.getRole().equals(UserRole.STUDENT)) {
            throw new IllegalStateException("L'utilisateur avec l'ID : " + userId + " n'est pas un étudiant.");
        }
        List<Projet> projets = projetRepository.findByTeamContaining(user);
        return projets.stream()
            .map(Projet::getProjetDto)
            .collect(Collectors.toList());
    }

	@Override
	public void deposerTravail(Long userId, Long tacheId, String contenu) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + userId));

	    if (!user.getRole().equals(UserRole.STUDENT)) {
	        throw new IllegalStateException("L'utilisateur avec l'ID : " + userId + " n'est pas un étudiant.");
	    }
	    Optional<Tache> tacheOptional = tacheRepository.findById(tacheId);
	    if (!tacheOptional.isPresent()) {
	        throw new IllegalStateException("La tâche avec l'ID : " + tacheId + " n'existe pas.");
	    }

	    Tache tache = tacheOptional.get();
	    Projet projet = tache.getProjet();

	    if (projet == null || !projet.getTeam().contains(user)) {
	        throw new IllegalStateException("L'utilisateur n'appartient pas à l'équipe du projet associé à la tâche.");
	    }

	    Depot depot = tache.getDepot();
	    depot.setContenu(contenu);
	    depot.setDateSoumission(new Date());
	    depot.setTache(tache);

	    depotRepository.save(depot);
	}

}

