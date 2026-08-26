package com.github.payment.api.application.ports.out;


import com.github.payment.api.domain.model.Payment;

public interface PaymentGatewayPort {
    /*
    * Processa a cobrança no gateway externo. Define o que o seu sistema precisa que qualquer gateway de pagamento.
    * Domínio — não sabe que existe Stripe.
    *
    * @param payment Objeto de dóminio com valor, moeda, etc.
    * @param idempotencyKey Chave de idempotência para evitar cobranças duplicadas na Stripe
    * @return PaymentGatewayReturn com o status e o ID da transação (ex> pi_3Nxxx...)
    */
    PaymentGatewayResult charge(Payment payment, String idempotencyKey);  // cobra
    // PaymentGatewayResult refund(Payment payment);  // estorna

    PaymentGatewayResult refund(String gatewayTransactionId);

}
