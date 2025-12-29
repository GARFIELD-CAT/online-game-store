package ru.utmn.online_game_store.model.external;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameShopKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer gameId;

    @Column(updatable = false)
    private String gameKey;

    @Column(updatable = false)
    private String gameName;

    @ManyToOne()
    @JoinColumn(name = "game_shop_record_id", nullable = false)
    private GameShopRecord gameShopRecord;

    @PrePersist
    void generateGameKey() {
        gameKey = UUID.randomUUID().toString();;
    }
}
