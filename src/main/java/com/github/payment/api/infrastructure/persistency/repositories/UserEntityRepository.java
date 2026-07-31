package com.github.payment.api.infrastructure.persistency.repositories;

import com.github.payment.api.infrastructure.persistency.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNameContainingIgnoreCase(String name);

    boolean existingEmail(boolean active);
}
