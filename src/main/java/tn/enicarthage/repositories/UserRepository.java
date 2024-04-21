package tn.enicarthage.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.entities.User;
import tn.enicarthage.enums.UserRole;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByRole(UserRole userRole);
	Optional<User> findFirstByEmail(String email);
	User findByEmailAndRole(String adminEmail, UserRole admin);
	List<User> findAllByRole(UserRole student);
	


}
