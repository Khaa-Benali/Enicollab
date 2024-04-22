package tn.enicarthage.services.taches;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.repositories.ProjetRepository;
import tn.enicarthage.repositories.TacheRepository;

@Service
public class TacheServiceImpl implements TacheService {
	
	private final ProjetRepository projetRepository;

	private final TacheRepository tacheRepository;
	
	 public TacheServiceImpl(TacheRepository tacheRepository, ProjetRepository projetRepository) {
	        this.projetRepository = projetRepository;
			this.tacheRepository = tacheRepository;
	    }
	   @Override
	    public List<Tache> getTachesByProjetId(Long projetId) {
	        return tacheRepository.findByProjetId(projetId);
	    }

	    @Override
	    public Tache updateTache(Long tacheId, Tache newTacheInfo) {
	        Tache existingTache = tacheRepository.findById(tacheId)
	                .orElseThrow(() -> new RuntimeException("Tache not found with id: " + tacheId));

	        // Mettre à jour les attributs de la tâche existante avec les nouvelles informations
	        existingTache.setDescription(newTacheInfo.getDescription());
	        existingTache.setDateEcheance(newTacheInfo.getDateEcheance());
	        existingTache.setNote(newTacheInfo.getNote());
	        existingTache.setCoefficient(newTacheInfo.getCoefficient());
	        existingTache.setTerminee(newTacheInfo.isTerminee());

	        // Enregistrer les modifications dans la base de données et retourner la tâche mise à jour
	        return tacheRepository.save(existingTache);
	    }

	    @Override
	    public void marquerTacheTerminee(Long tacheId) {
	        Tache tache = tacheRepository.findById(tacheId)
	                .orElseThrow(() -> new RuntimeException("Tache not found with id: " + tacheId));

	        // Marquer la tâche comme terminée
	        tache.setTerminee(true);
	    }

	    @Override
	    public boolean isTacheEnRetard(Long tacheId) {
	        Tache tache = tacheRepository.findById(tacheId)
	                .orElseThrow(() -> new RuntimeException("Tache not found with id: " + tacheId));
	        return tache.getDateEcheance().before(new Date());
	    }
		@Override
		public Tache addTacheToProjet(Long projetId, Tache tache) {
			 Optional<Projet> optionalProjet = projetRepository.findById(projetId);
		        if (optionalProjet.isPresent()) {
		            Projet projet = optionalProjet.get();
		            tache.setProjet(projet); 
		            return tacheRepository.save(tache); 
		        } else {
		            throw new RuntimeException("Projet not found with id: " + projetId);
		        }
		}
		
}
