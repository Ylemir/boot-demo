package me.boot.datarest.repository;

import java.util.Optional;
import me.boot.datarest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);
}
