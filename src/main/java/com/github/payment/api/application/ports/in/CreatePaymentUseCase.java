package com.github.payment.api.application.ports.in;

import com.github.payment.api.application.dtos.request.CreatePaymentRequest;
import com.github.payment.api.application.dtos.response.PaymentCreateResponse;

import java.util.UUID;

public interface CreatePaymentUseCase {

    PaymentCreateResponse createPayment(CreatePaymentRequest request, UUID userId, UUID idempotencyKey);
}
