package com.github.payment.api.application.ports.out;

import com.github.payment.api.domain.model.IdempotencyKey;

import java.util.Optional;
import java.util.UUID;

public interface IdempotencyKeyRepository {

    IdempotencyKey save(IdempotencyKey key);

    Optional<IdempotencyKey> findByKey(UUID key);
}
