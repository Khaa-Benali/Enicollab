package tn.enicarthage.services.teacher;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.enicarthage.dto.MatiereDto;
import tn.enicarthage.dto.ProjetDto;
import tn.enicarthage.entities.Depot;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Projet;
import tn.enicarthage.entities.Tache;
import tn.enicarthage.entities.Teacher;
import tn.enicarthage.entities.User;
import tn.enicarthage.enums.EtatProjet;
import tn.enicarthage.enums.UserRole;
import tn.enicarthage.repositories.DepotRepository;
import tn.enicarthage.repositories.MatiereRepository;
import tn.enicarthage.repositories.ProjetRepository;
import tn.enicarthage.repositories.TacheRepository;
import tn.enicarthage.repositories.TeacherRepository;
import tn.enicarthage.repositories.UserRepository;

@Service
public class TeacherServiceImpll implements TeacherService{
	@Autowired
    private DepotRepository depotRepository;
	
	@Autowired
    private TacheRepository tacheRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private ProjetRepository projetRepository;
	
	@Autowired 
	private MatiereRepository matiereRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	@Override
    public Optional<Teacher> findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }
	 @Override
	 public Projet saveProjet(Projet projet) {
	     return projetRepository.save(projet);
	 }
	 @Override
	    public Matiere addProjetToMatiere(Long id, Projet projet) {
	        Optional<Matiere> optionalMatiere = matiereRepository.findById(id);
	        if (optionalMatiere.isPresent()) {
	            Matiere matiere = optionalMatiere.get();
	            projet.setMatiere(matiere); 
	            projet.setEtatProjet(EtatProjet.EN_COURS); 
	            projetRepository.save(projet);
	            matiere.getProjets().add(projet); 
	            return matiereRepository.save(matiere);
	        } else {
	            throw new RuntimeException("Matiere not found with id: " + id);
	        }
	    }

	 @Override
	 public void assignTeamToProjet(Long idProjet, List<String> emails) {
	     Optional<Projet> optionalProjet = projetRepository.findById(idProjet);
	     if (optionalProjet.isPresent()) {
	         Projet projet = optionalProjet.get();

	         List<User> equipe = new ArrayList<>();
	         for (String email : emails) {
	             User etudiant = userRepository.findFirstByEmail(email)
	                 .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé pour l'email : " + email));

	             if (etudiant.getRole() != UserRole.STUDENT) {
	                 throw new IllegalArgumentException("L'utilisateur n'est pas un étudiant.");
	             }

	             equipe.add(etudiant);
	         }

	         projet.setTeam(equipe);
	         projetRepository.save(projet);
	     } else {
	         throw new RuntimeException("Projet non trouvé avec l'identifiant : " + idProjet);
	     }}
	    @Override
	    @Transactional 
	    public Tache addTacheToProjet(Long projetId, Tache tache) {
	        Optional<Projet> optionalProjet = projetRepository.findById(projetId);
	        if (optionalProjet.isPresent()) {
	            Projet projet = optionalProjet.get();
	            Depot depot = new Depot();
	            depot.setContenu(""); 
	            depot.setTache(tache);

	            Depot savedDepot = depotRepository.save(depot);

	            tache.setDepot(savedDepot);

	            tache.setProjet(projet);
	            return tacheRepository.save(tache);
	        } else {
	            throw new RuntimeException("Projet not found with id: " + projetId);
	        }
	    }
		
	    @Override
	    public Depot saveDepot(Depot depot) {
	        return depotRepository.save(depot);
	    }
	    @Override
	    @Transactional
	    public void noterTache(Long tacheId, double note) {

	        Optional<Tache> optionalTache = tacheRepository.findById(tacheId);
	        if (optionalTache.isPresent()) {
	            Tache tache = optionalTache.get();
	            Date dateSoumission = (Date) tache.getDepot().getDateSoumission();
	            Date dateEcheance = (Date) tache.getDateEcheance();
	            if (dateSoumission != null && dateSoumission.before(dateEcheance)) {
	                tache.setNote(note);
	                tacheRepository.save(tache);
	            } else {
	                throw new IllegalArgumentException("La date de soumission est nulle ou postérieure à la date d'échéance.");
	            }
	        } else {
	            throw new IllegalArgumentException("Tâche non trouvée avec l'ID : " + tacheId);
	        }
	    }
	    
	    @Override
	    public double calculNoteFinal(Long id) {
	        Projet projet = projetRepository.getProjetById(id);
	        double noteFinale = 0;
	        List<Tache> taches = projet.getTaches();
	        for (Tache tache : taches) {
	            noteFinale += tache.getNote();
	        }
	        return noteFinale;
	    }
		@Override
		public List<Projet> getAllProjets() {
			return projetRepository.findAll();
		}
		@Override
	    public List<ProjetDto> getAllProjects() {
	        List<Projet> projets = projetRepository.findAll();
	        return projets.stream()
	                .map(Projet::getProjetDto)
	                .collect(Collectors.toList());
	    }
	    @Override
	    public List<MatiereDto> getAllMatieres(){
	    	List<Matiere> matieres = matiereRepository.findAll();
	    	return matieres.stream()
	    			.map(Matiere::getMatiereDto)
	                .collect(Collectors.toList());
	    	
	    }
	        

}
