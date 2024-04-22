package tn.enicarthage.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.entities.User;
import tn.enicarthage.repository.ProjetRepository;
import tn.enicarthage.repository.TacheRepository;
import tn.enicarthage.repository.UserRepository;

@Service
public class TacheServiceImpl implements TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjetRepository projetRepository;

    @Override
    public Tache assignerTache(Long tacheId, Long userId) {
        Optional<Tache> optionalTache = tacheRepository.findById(tacheId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalTache.isPresent() && optionalUser.isPresent()) {
            Tache tache = optionalTache.get();
            User user = optionalUser.get();
            tache.setAssignationUser(user);
            return tacheRepository.save(tache);
        } else {
            // Gérer le cas où la tâche ou l'utilisateur n'est pas trouvé
            return null;
        }
    }

    @Override
    public List<Tache> getTachesByUserId(Long userId) {
        return tacheRepository.findByAssignationUserId(userId);
    }

    @Override
    public List<Tache> getTachesDateEcheanceDepassee() {
    	Date currentDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        return tacheRepository.findByDateEcheanceBeforeAndAssignationUserNotNull(sqlDate);
    }

    @Override
    public boolean isTacheAssignee(Long tacheId, Long userId) {
        Optional<Tache> optionalTache = tacheRepository.findById(tacheId);
        if (optionalTache.isPresent()) {
            Tache tache = optionalTache.get();
            return tache.getAssignationUser() != null && tache.getAssignationUser().getId().equals(userId);
        }
        return false;
    }

    @Override
    public Tache updateTache(Long tacheId, Tache newTacheInfo) {
        Optional<Tache> optionalTache = tacheRepository.findById(tacheId);
        if (optionalTache.isPresent()) {
            Tache tache = optionalTache.get();
            tache.setDateEcheance(newTacheInfo.getDateEcheance());
            tache.setNotes(newTacheInfo.getNotes());
            tache.setCoefficient(newTacheInfo.getCoefficient());
            return tacheRepository.save(tache);
        }
        return null;
    }

    @Override
    public void marquerTacheTerminee(Long tacheId) {
        Optional<Tache> optionalTache = tacheRepository.findById(tacheId);
        if (optionalTache.isPresent()) {
            Tache tache = optionalTache.get();
            tache.setTerminee(true); // Marquer la tâche comme terminée
            tacheRepository.save(tache); // Enregistrer la tâche mise à jour dans la base de données
        }
    }

    @Override
    public void supprimerTachesUtilisateur(Long userId) {
        List<Tache> taches = tacheRepository.findByAssignationUserId(userId);
        tacheRepository.deleteAll(taches);
    }

    @Override
    public boolean isTacheEnRetard(Long tacheId) {
        Optional<Tache> optionalTache = tacheRepository.findById(tacheId);
        if (optionalTache.isPresent()) {
            Tache tache = optionalTache.get();
            // Comparer la date d'échéance avec la date actuelle
            return tache.getDateEcheance().before(new Date());
        }
        return false;
    }
    @Override
    public Tache addTacheToProjet(Long projetId, Tache tache) {
        Optional<Projet> optionalProjet = projetRepository.findById(projetId);
        if (optionalProjet.isPresent()) {
            Projet projet = optionalProjet.get();
            tache.setProjet(projet); // Assigne le projet à la tâche
            // Définir d'autres propriétés de la tâche si nécessaire
            return tacheRepository.save(tache); // Enregistre la tâche
        } else {
            throw new RuntimeException("Projet not found with id: " + projetId);
        }
    }

}
