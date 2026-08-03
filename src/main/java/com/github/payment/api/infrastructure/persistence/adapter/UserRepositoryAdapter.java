package com.github.payment.api.infrastructure.persistence.adapter;

import com.github.payment.api.application.ports.out.UserRepository;
import com.github.payment.api.domain.model.User;
import com.github.payment.api.infrastructure.persistence.entity.UserEntity;
import com.github.payment.api.infrastructure.persistence.mapper.UserEntityMapper;
import com.github.payment.api.infrastructure.persistence.repositories.UserEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepository {
    //TODO: VERIFY ADAPTER
    private final UserEntityRepository userEntityRepository;

    public UserRepositoryAdapter(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntityMapper.toEntity(user);
        UserEntity saved = userEntityRepository.save(entity);
        return UserEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userEntityRepository.findById(id)
                .map(UserEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityRepository.findByEmail(email)
                .map(UserEntityMapper::toDomain);
    }

    @Override
    public List<User> findByNameContainingIgnoreCase(String name) {
        return userEntityRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(UserEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userEntityRepository.existsByEmail(email);
    }
}
