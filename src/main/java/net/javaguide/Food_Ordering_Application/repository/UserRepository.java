package net.javaguide.Food_Ordering_Application.repository;

import net.javaguide.Food_Ordering_Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
 @Repository                                          //type of the primary key
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsername(String username);
//    Boolean existsByUsername(String username);
}
