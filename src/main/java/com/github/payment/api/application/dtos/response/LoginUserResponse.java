package com.github.payment.api.application.dtos.response;

import com.github.payment.api.domain.enuns.RoleUser;

import java.time.OffsetDateTime;
import java.util.UUID;

public record LoginUserResponse(String token) {
}
