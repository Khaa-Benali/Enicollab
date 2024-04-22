package tn.enicarthage.services.niveaux;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.enicarthage.entities.Niveau;
import tn.enicarthage.repositories.NiveauRepository;

@Service
@Transactional
public class NiveauServiceImpl implements NiveauService {

    private final NiveauRepository niveauRepository;

    @Autowired
    public NiveauServiceImpl(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    @Override
    public List<Niveau> getAllNiveaux() {
        return niveauRepository.findAll();
    }

    @Override
    public Niveau getNiveauById(Long id) {
        return niveauRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Niveau not found with id: " + id));
    }

    @Override
    public Niveau createNiveau(Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    @Override
    public Niveau updateNiveau(Long id, Niveau newNiveauInfo) {
        Niveau existingNiveau = niveauRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Niveau not found with id: " + id));

        existingNiveau.setNom(newNiveauInfo.getNom());

        return niveauRepository.save(existingNiveau);
    }

    @Override
    public void deleteNiveau(Long id) {
        niveauRepository.deleteById(id);
    }
}