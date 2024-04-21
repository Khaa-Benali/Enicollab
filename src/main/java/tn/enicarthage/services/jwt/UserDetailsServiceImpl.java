package tn.enicarthage.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Teacher;
import tn.enicarthage.entities.User;
import tn.enicarthage.repositories.UserRepository;
import tn.enicarthage.services.teacher.TeacherService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	 @Autowired
	    private TeacherService teacherService;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        // Essayez de charger l'utilisateur depuis la classe User
	        Optional<User> userOptional = userRepository.findFirstByEmail(username);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            return new org.springframework.security.core.userdetails.User(
	                    user.getEmail(),
	                    user.getPassword(),
	                    new ArrayList<>()
	            );
	        }

	        // Si l'utilisateur n'est pas trouvé dans la classe User, essayez de le charger depuis la classe Teacher
	        Optional<Teacher> teacherOptional = teacherService.findByEmail(username);
	        if (teacherOptional.isPresent()) {
	            Teacher teacher = teacherOptional.get();
	            return new org.springframework.security.core.userdetails.User(
	                    teacher.getEmail(),
	                    teacher.getPassword(), // Assurez-vous de hacher le mot de passe si nécessaire
	                    new ArrayList<>()
	            );
	        }

	        throw new UsernameNotFoundException("Username not found");
	    }
}
