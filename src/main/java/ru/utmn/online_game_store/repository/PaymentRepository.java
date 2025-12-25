package ru.utmn.online_game_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.utmn.online_game_store.model.external.Payment;

import java.util.Optional;


//По сути Mock над внешним сервисом оплаты
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByOrderId(Integer orderId);
}
