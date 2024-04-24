package tn.enicarthage.services.student;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.enicarthage.dto.ProjetDto;

@Service
public interface StudentService {

	List<ProjetDto> getProjetsByUserId(Long userId);

	void deposerTravail(Long userId, Long tacheId, String contenu);

}