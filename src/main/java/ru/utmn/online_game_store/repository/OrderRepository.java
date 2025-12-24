package ru.utmn.online_game_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.utmn.online_game_store.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
