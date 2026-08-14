package com.github.payment.api.application.mapper;

import com.github.payment.api.application.dtos.request.CreatePaymentRequest;
import com.github.payment.api.application.dtos.response.PaymentCreateResponse;
import com.github.payment.api.domain.model.Payment;

import java.util.UUID;

public class PaymentMapper {

    // Request -> Domain
    public static Payment toDomain(CreatePaymentRequest request, UUID userId){
        return Payment.create(
                request.amount(),
                request.currency(),
                request.description(),
                request.paymentMethod(),
                request.paymentMethodId(),
                userId

        );
    }

    // Domain -> Response
    public static PaymentCreateResponse toResponse(Payment payment){
        return new PaymentCreateResponse(
                payment.getId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getDescription(),
                payment.getStatus(),
                payment.getPaymentMethod(),
                payment.getPaymentMethodId(),
                payment.getGatewayTransactionId(),
                payment.getCreatedAt(),
                payment.getUpdatedAt(),
                payment.getRetry()

        );
    }
}
