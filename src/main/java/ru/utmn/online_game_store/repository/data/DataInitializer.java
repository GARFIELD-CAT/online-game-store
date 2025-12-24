package ru.utmn.online_game_store.repository.data;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.utmn.online_game_store.model.User;
import ru.utmn.online_game_store.repository.UserRepository;
import ru.utmn.online_game_store.service.PasswordService;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;

    @PostConstruct
    public void createInitialUsers() {
        if (userRepository.count() == 0) {
            User user1 = new User(
                    "Илон",
                    "Маск",
                    "admin@gmail.com",
                    passwordService.hashPassword("adminPass"),
                    LocalDate.of(1990, 1, 1),
                    "ADMIN"
            );
            User user2 = new User(
                    "Денис",
                    "Квашин",
                    "user@gmail.com",
                    passwordService.hashPassword("userPass"),
                    LocalDate.of(2005, 1, 1),
                    "USER"
            );

            ArrayList<User> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);

            userRepository.saveAll(users);
        }
    }
}