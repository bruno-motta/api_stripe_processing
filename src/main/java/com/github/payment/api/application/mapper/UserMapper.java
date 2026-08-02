package com.github.payment.api.application.mapper;

import com.github.payment.api.application.dtos.request.RegisterUserRequest;
import com.github.payment.api.application.dtos.response.RegisterUserResponse;
import com.github.payment.api.domain.model.User;

public class UserMapper {

    // Request → Domain
    public static User toDomain(RegisterUserRequest request, String passwordHash) {
        return User.create(
                request.name(),
                request.email(),
                passwordHash
        );
    }

    // Domain → Response
    public static RegisterUserResponse toResponse(User user) {
        return new RegisterUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.isActive()
        );
    }

}
