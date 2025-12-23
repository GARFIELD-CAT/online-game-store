package ru.utmn.online_game_store.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String developerName;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String platform = Platform.PC.getValue();

    @Column(nullable = false)
    private boolean isAvailable = true;

    @Column(nullable = false)
    private double averageUserRating;

    @Column(nullable = false)
    private String ageRating = AgeRating.TWELWE.getValue();
}
