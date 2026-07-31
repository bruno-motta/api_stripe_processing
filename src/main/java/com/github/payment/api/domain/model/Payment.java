package com.github.payment.api.domain.model;

import com.github.payment.api.domain.enuns.PaymentMethod;
import com.github.payment.api.domain.enuns.StatusPayment;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.UUID;

@Getter
public class Payment {

    private UUID id;
    private UUID userId;
    private BigDecimal amount;
    private Currency currency;
    private String description;
    private StatusPayment status;
    private PaymentMethod paymentMethod;
    private String paymentMethodId;
    private String gatewayTransactionId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private int retry;

    private static final int RETRY_COUNT = 3;


}
