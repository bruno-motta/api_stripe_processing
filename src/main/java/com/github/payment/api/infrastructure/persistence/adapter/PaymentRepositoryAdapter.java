package com.github.payment.api.infrastructure.persistence.adapter;

import com.github.payment.api.application.ports.out.PaymentRepository;
import com.github.payment.api.domain.model.Payment;
import com.github.payment.api.infrastructure.persistence.entity.PaymentEntity;
import com.github.payment.api.infrastructure.persistence.mapper.PaymentEntityMapper;
import com.github.payment.api.infrastructure.persistence.repositories.PaymentEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentEntityRepository paymentEntityRepository;

    public PaymentRepositoryAdapter(PaymentEntityRepository paymentEntityRepository){
        this.paymentEntityRepository = paymentEntityRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity = PaymentEntityMapper.toEntity(payment);
        PaymentEntity savedEntity = paymentEntityRepository.save(entity);
        return PaymentEntityMapper.toDomain(savedEntity);

    }

    @Override
    public Optional<Payment> findById(UUID uuid) {
        return paymentEntityRepository.findById(uuid)
                .map(PaymentEntityMapper::toDomain);
    }

    @Override
    public Optional<Payment> findByIdAndUserId(UUID id, UUID idUser) {
        return paymentEntityRepository.findByIdAndUser_Id(id, idUser)
                .map(PaymentEntityMapper::toDomain);
    }

    @Override
    public List<Payment> findAllByUserId(UUID id) {
        return paymentEntityRepository.findAllByUser_Id(id)
                .stream()
                .map(PaymentEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Payment> findRetryable() {
        return paymentEntityRepository.findRetryable()
                .stream()
                .map(PaymentEntityMapper::toDomain)
                .toList();
    }
}
