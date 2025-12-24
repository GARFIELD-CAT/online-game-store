package ru.utmn.online_game_store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


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

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany()
    @JoinColumn(name = "game_ids", nullable = false)
    private List<Game> games;
}
