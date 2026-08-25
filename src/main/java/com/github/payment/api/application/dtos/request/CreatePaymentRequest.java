package com.github.payment.api.application.dtos.request;

import com.github.payment.api.domain.enuns.Currency;
import com.github.payment.api.domain.enuns.PaymentMethod;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentRequest(

        @NotNull
        @Positive
        @Digits(integer = 19, fraction = 2)
        BigDecimal amount,

//        @NotBlank
//        @Pattern(regexp = "^[A-Z]{3}$")
        String currency,

        @Size(max = 200)
        String description,

        @NotNull
        PaymentMethod paymentMethod,

        @NotBlank
        String paymentMethodId
) {
}
