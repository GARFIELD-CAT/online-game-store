package ru.utmn.online_game_store.model.external;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.utmn.online_game_store.model.GameOrder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameShopRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private GameOrder order;

    @OneToMany(mappedBy = "gameShopRecord")
    private List<GameShopKey> keys;

    @Column(nullable = false)
    private LocalDateTime created;

    @PrePersist
    void onCreate() {
        created = LocalDateTime.now();
    }
}
