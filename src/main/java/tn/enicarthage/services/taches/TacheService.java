package tn.enicarthage.services.taches;

import java.util.List;

import tn.enicarthage.entities.Tache;

public interface TacheService {
   
    List<Tache> getTachesByProjetId(Long projetId);
    Tache updateTache(Long tacheId, Tache newTacheInfo);
    void marquerTacheTerminee(Long tacheId);

    boolean isTacheEnRetard(Long tacheId);
    
    Tache addTacheToProjet(Long projetId, Tache tache);
    
    


}
