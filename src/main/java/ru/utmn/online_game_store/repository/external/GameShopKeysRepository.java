package ru.utmn.online_game_store.repository.external;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.utmn.online_game_store.model.external.GameShopKey;

@Repository
public interface GameShopKeysRepository extends JpaRepository<GameShopKey, Integer> {
}
