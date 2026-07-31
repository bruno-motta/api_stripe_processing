package com.github.payment.api.application.ports.out;

import com.github.payment.api.domain.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> save(User save);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findByNameContainingIgnoreCase(String name);
    boolean existingEmail(boolean existingEmail);

}
