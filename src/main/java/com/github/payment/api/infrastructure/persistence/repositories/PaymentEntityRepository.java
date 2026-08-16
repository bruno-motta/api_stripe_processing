package com.github.payment.api.infrastructure.persistence.repositories;

import com.github.payment.api.infrastructure.persistence.entity.PaymentEntity;
import com.github.payment.api.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findByIdAndUser_Id(UUID id, UUID userId);

    List<PaymentEntity> findAllByUser_Id(UUID userId);

    //Query JPQL, criada p
    @Query("SELECT p FROM PaymentEntity p WHERE p.statusPayment = 'FAILED' AND p.retry < 3")
    List<PaymentEntity> findRetryable();
}
