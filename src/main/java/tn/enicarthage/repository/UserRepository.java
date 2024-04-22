package tn.enicarthage.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.User;
import tn.enicarthage.enums.Role;
@Repository

public interface UserRepository extends JpaRepository<User,Long>{

	User findByRole(Role userRole);
	Optional<User> findFirstByEmail(String email);
	


}