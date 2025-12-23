package ru.utmn.online_game_store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private String status = OrderStatus.PENDING.getValue();

    @Column(nullable = false)
    private double totalAmount;

    @PrePersist
    void onCreate() {
        created = LocalDateTime.now();
    }
}
