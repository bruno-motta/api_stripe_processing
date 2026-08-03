package com.github.payment.api.infrastructure.persistence.mapper;

import com.github.payment.api.domain.model.User;
import com.github.payment.api.infrastructure.persistence.entity.UserEntity;

public class UserEntityMapper {

    // Domain → Entity (para salvar no banco)
    public static UserEntity toEntity(User user){
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setRole(user.getRole());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        entity.setActive(user.isActive());

        return entity;
    }

    // Entity -> Domain (ao ler o banco)
    public static User toDomain(UserEntity entity){
        return User.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isActive()
        );
    }

}
