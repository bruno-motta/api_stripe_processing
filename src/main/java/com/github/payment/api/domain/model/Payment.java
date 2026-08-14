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

    private static final int MAX_RETRIES = 3;

    private Payment(){

    }

    public static Payment create(BigDecimal amount, Currency currency, String description,
                                 PaymentMethod  paymentMethod,String paymentMethodId, UUID userId){

        validateAmount(amount);
        validateCurrency(currency);
        validatePaymentMethod(paymentMethod);
        validateUserId(userId);
        validatePaymentMethodId(paymentMethodId);

        OffsetDateTime now = OffsetDateTime.now();

        Payment payment = new Payment();
        payment.id = UUID.randomUUID();
        payment.userId = userId;
        payment.amount = amount;
        payment.currency = currency;
        payment.description = description;
        payment.status = StatusPayment.PENDING;
        payment.paymentMethod = paymentMethod;
        payment.paymentMethodId = paymentMethodId;
        payment.createdAt = now;
        payment.updatedAt = now;
        payment.retry = 0;

        return payment;
    }

    public void processing(){
        if(this.status != StatusPayment.PENDING){
            throw new IllegalArgumentException( "Status do pagamento precisa estar PENDING para processar, Status atual: " + this.status);
        }
        this.status = StatusPayment.PROCESSING;
        this.updatedAt = OffsetDateTime.now();
    }

    public void approve(String gatewayTransactionId){
        if(this.status != StatusPayment.PROCESSING){
            throw new IllegalArgumentException("Status do pagamento precisa estar PROCESSING para aprovar, Status atual: " + this.status);
        }

        if(gatewayTransactionId == null || gatewayTransactionId.isBlank()){
            throw  new IllegalArgumentException("O ID da transação do gateway é obrigatório para aprovação.");
        }

        this.gatewayTransactionId = gatewayTransactionId;
        this.status = StatusPayment.APPROVED;
        this.updatedAt = OffsetDateTime.now();
    }

    public void fail(){
        if(this.status != StatusPayment.PROCESSING){
            throw new IllegalArgumentException("Status do pagemento precisa estar PROCESSING para falhar. Status atual: " +  this.status);
        }

        this.status = StatusPayment.FAILED;
        this.retry++;
        this.updatedAt = OffsetDateTime.now();
    }

    public void refund(){
        if(this.status != StatusPayment.APPROVED){
            throw new IllegalArgumentException("Status do pagamento precisa estar APPROVE para reembolso. Status atual: " + this.status);
        }

        this.status = StatusPayment.REFUNDED;
        this.updatedAt = OffsetDateTime.now();
    }

    public boolean isRetryable() {
        return this.status == StatusPayment.FAILED && this.retry < MAX_RETRIES;
    }

    // TODO: Revisar a lógica de retentativas.
    /* Atualmente é permitido transicionar de FAILED de volta para PROCESSING caso ainda
    não tenha atingido o limite máximo (MAX_RETRIES). Garante que pagamentos
     aprovados/estornados não sejam reprocessados por erro.
     */
    public void retry(){
        if(this.status != StatusPayment.FAILED){
            throw new IllegalArgumentException("Falha no pagamento, inicie o processo novamente");
        }

        this.status = StatusPayment.PROCESSING;
        this.updatedAt = OffsetDateTime.now();
    }

    private static void validateAmount(BigDecimal amount){
        if(amount == null){
            throw new IllegalArgumentException("Valor do pagamento é obrigatório.");
        }

        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor do pagamento precisa ser maior que zero. Valor informado: " + amount);
        }

        if(amount.scale() > 2){
            throw new IllegalArgumentException("Pagamento deve possuir no máximo duas casas decimais.");
        }
    }

    private static void validateCurrency(Currency currency){
        if(currency == null){
            throw new IllegalArgumentException("Moeda é obrigatória");
        }
    }

    private static void validatePaymentMethod(PaymentMethod paymentMethod){
        if(paymentMethod == null){
            throw new IllegalArgumentException("Método de pagamento é obrigatório");
        }
    }

    private static void validateUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("ID do usuário é obrigatório");
        }
    }

    private static void validatePaymentMethodId(String paymentMethodId) {
        if (paymentMethodId == null || paymentMethodId.isBlank()) {
            throw new IllegalArgumentException(
                    "ID do método de pagamento é obrigatório"
            );
        }
    }
}
