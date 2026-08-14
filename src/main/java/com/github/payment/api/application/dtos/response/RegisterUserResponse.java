package com.github.payment.api.application.dtos.response;

import com.github.payment.api.domain.enuns.RoleUser;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RegisterUserResponse(UUID id,
                                   String name,
                                   String email,
                                   RoleUser role,
                                   OffsetDateTime createdAt,
                                   OffsetDateTime updatedeAt,
                                   boolean active
) {
}
