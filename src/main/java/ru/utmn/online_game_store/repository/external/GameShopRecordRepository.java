package ru.utmn.online_game_store.repository.external;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.utmn.online_game_store.model.external.GameShopRecord;

import java.util.Optional;

@Repository
public interface GameShopRecordRepository extends JpaRepository<GameShopRecord, Integer> {
    Optional<GameShopRecord> findByOrderId(@Param("order_id") Integer orderId);
}
