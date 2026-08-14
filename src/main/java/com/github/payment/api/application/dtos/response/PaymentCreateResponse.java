package com.github.payment.api.application.dtos.response;

import com.github.payment.api.domain.enuns.PaymentMethod;
import com.github.payment.api.domain.enuns.StatusPayment;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.UUID;

public record PaymentCreateResponse(UUID id, UUID userId, BigDecimal amount,
                                    Currency currency, String description, StatusPayment status,
                                    PaymentMethod paymentMethod, String paymentMethodId, String gatewayTransactionId,
                                    OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
