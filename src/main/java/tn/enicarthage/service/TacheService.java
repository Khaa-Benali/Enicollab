package tn.enicarthage.service;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.entities.User;

@Service
public interface TacheService {
	// Assigner une tâche à un utilisateur
    Tache assignerTache(Long tacheId, Long userId);
    
    // Récupérer toutes les tâches assignées à un utilisateur
    List<Tache> getTachesByUserId(Long userId);
    
    // Récupérer toutes les tâches dont la date d'échéance est dépassée
    List<Tache> getTachesDateEcheanceDepassee();
    
    // Vérifier si une tâche est assignée à un utilisateur
    boolean isTacheAssignee(Long tacheId, Long userId);
    
    // Mettre à jour les informations d'une tâche
    Tache updateTache(Long tacheId, Tache newTacheInfo);
    
    // Marquer une tâche comme terminée
    void marquerTacheTerminee(Long tacheId);
    
    // Supprimer toutes les tâches assignées à un utilisateur
    void supprimerTachesUtilisateur(Long userId);
    
    // Vérifier si une tâche est en retard par rapport à sa date d'échéance
    boolean isTacheEnRetard(Long tacheId);
}
