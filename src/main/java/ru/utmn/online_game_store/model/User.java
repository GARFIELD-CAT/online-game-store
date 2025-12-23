package ru.utmn.online_game_store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    private String firstName;

    @Column(nullable = false, length = 128)
    private String lastName;

    @Column(nullable = false, length = 128, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String role = "USER";

    @Column(updatable = false)
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime updated;

    @Column(nullable = false)
    private boolean enabled = true;

    public User(String firstName, String lastName, String email, String password, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.age = Period.between(birthday, LocalDate.now()).getYears();
    }

    @PrePersist
    void onCreate() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updated = LocalDateTime.now();
    }
}
