package com.github.payment.api.infrastructure.persistence.mapper;

import com.github.payment.api.domain.model.Payment;
import com.github.payment.api.infrastructure.persistence.entity.PaymentEntity;
import com.github.payment.api.infrastructure.persistence.entity.UserEntity;

public class PaymentEntityMapper {

    // Domain → Entity
    public static PaymentEntity toEntity(Payment payment) {

        PaymentEntity entity = new PaymentEntity();

        entity.setId(payment.getId());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(payment.getUserId());

        entity.setUser(userEntity);

        entity.setAmount(payment.getAmount());
        entity.setCurrency(payment.getCurrency());
        entity.setDescription(payment.getDescription());
        entity.setStatusPayment(payment.getStatus());
        entity.setPaymentMethod(payment.getPaymentMethod());
        entity.setPaymentMethodId(payment.getPaymentMethodId());
        entity.setGatewayTransactionId(payment.getGatewayTransactionId());
        entity.setCreatedAt(payment.getCreatedAt());
        entity.setUpdatedAt(payment.getUpdatedAt());
        entity.setRetry(payment.getRetry());

        return entity;
    }
    // Entity -> Domain
    public static Payment toDomain(PaymentEntity entity) {

        return Payment.reconstitute(
                entity.getId(),
                entity.getUser().getId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getDescription(),
                entity.getStatusPayment(),
                entity.getPaymentMethod(),
                entity.getPaymentMethodId(),
                entity.getGatewayTransactionId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getRetry()
        );
    }
}
