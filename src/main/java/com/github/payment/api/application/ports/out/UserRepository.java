package com.github.payment.api.application.ports.out;

import com.github.payment.api.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User save);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String existingEmail);

}

