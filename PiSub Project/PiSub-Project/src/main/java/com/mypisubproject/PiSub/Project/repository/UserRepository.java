package com.mypisubproject.PiSub.Project.repository;

import com.mypisubproject.PiSub.Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsByEmail(String email);
    public User findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

}
