package com.github.payment.api.application.ports.out;

public record PaymentGatewayResult(
        // Objeto que carrega a resposta resumida e traduzida das Stripe de volta para a aplivação
        String chargeId, // Identificador retor
        boolean success

) {

}
