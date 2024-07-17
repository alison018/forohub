package com.forhub.api.repository;

import com.forhub.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Encontrar usuarios por participación en el curso
    List<User> findByCoursesId(Long courseId);
    boolean existsByUsername(String username);
}