package ru.utmn.online_game_store.model.external;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.utmn.online_game_store.model.GameOrder;

//По сути Mock над внешним сервисом оплаты
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private GameOrder order;
}
