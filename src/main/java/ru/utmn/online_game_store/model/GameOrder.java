package ru.utmn.online_game_store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private String status = OrderStatus.PENDING.getValue();

    @Column(nullable = false)
    private double totalAmount;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "orders")
    private List<Game> games = new ArrayList<>();

    @PrePersist
    void onCreate() {
        created = LocalDateTime.now();
    }
}
