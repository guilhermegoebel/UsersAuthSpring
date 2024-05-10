package usersAuth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import usersAuth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String username);
}
