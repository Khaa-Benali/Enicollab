package tn.enicarthage.services.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import tn.enicarthage.entities.User;
import tn.enicarthage.enums.UserRole;
import tn.enicarthage.repositories.UserRepository;

@Service
public class AdminServiceImpl {
	
	private final UserRepository userRepository;
	
	public AdminServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(adminAccount == null) {
		User admin = new User();
		admin.setEmail("admin@test.com");
		admin.setName("admin");
		admin.setRole(UserRole.ADMIN);
		admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
	    userRepository.save(admin);
		}
	}
}
