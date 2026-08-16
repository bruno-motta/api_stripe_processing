package com.github.payment.api.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "idempotency_key")
public class IdempotencyKeyEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID key;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="payment_id", nullable = false )
    private PaymentEntity paymentId;

    @Column(name = "response_body", nullable = false)
    private String responseBody;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
