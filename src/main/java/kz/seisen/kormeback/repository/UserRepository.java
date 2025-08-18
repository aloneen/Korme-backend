package kz.seisen.kormeback.repository;

import jdk.jfr.Registered;
import kz.seisen.kormeback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}