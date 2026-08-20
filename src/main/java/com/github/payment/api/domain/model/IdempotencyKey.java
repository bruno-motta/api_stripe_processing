package com.github.payment.api.domain.model;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class IdempotencyKey {

    private  UUID id;
    private  UUID key; // chave enviada pelo cliente no header
    private  UUID paymentId; // referência ao pagamento criado
    private  String responseBody; //  JSON da resposta original para devolver igual
    private  OffsetDateTime createdAt;



    public static IdempotencyKey create(UUID key, UUID paymentId, String responseBody){
        if(key == null){
            throw new IllegalArgumentException("Idempotency key não pode ser nula.");
        }

        if(paymentId == null){
            throw new IllegalArgumentException("Payment ID não pode ser nulo.");
        }

        if(responseBody == null || responseBody.isBlank()){
            throw new IllegalArgumentException("Response body não pode ser nulo ou vazio.");
        }

        IdempotencyKey idempotencyKey = new IdempotencyKey();
        idempotencyKey.id = UUID.randomUUID();
        idempotencyKey.key = key;
        idempotencyKey.paymentId = paymentId;
        idempotencyKey.responseBody = responseBody;
        idempotencyKey.createdAt = OffsetDateTime.now();

        return idempotencyKey;
    }

    public static IdempotencyKey reconstitute(UUID id, UUID key, UUID paymentId, String responseBody, OffsetDateTime createdAt){

        if (id == null || key == null || paymentId == null || createdAt == null) {
            throw new IllegalArgumentException("Dados de reconstituição de IdempotencyKey não podem conter campos nulos.");
        }
        
        IdempotencyKey idempotencyKey = new IdempotencyKey();
        idempotencyKey.id = id;
        idempotencyKey.key = key;
        idempotencyKey.paymentId = paymentId;
        idempotencyKey.responseBody = responseBody;
        idempotencyKey.createdAt = createdAt;

        return idempotencyKey;
    }

}
