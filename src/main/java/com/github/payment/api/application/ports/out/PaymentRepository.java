package com.github.payment.api.application.ports.out;

import com.github.payment.api.domain.model.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Payment save(Payment save);
    Optional<UUID> findById(UUID uuid);
    Optional<Payment> findByIdAndUserId(UUID id, UUID idUser);
    List<Payment> findAllByUserId(UUID id);
    List<Payment> findRetryable();

}
