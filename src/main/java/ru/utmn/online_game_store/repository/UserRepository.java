package ru.utmn.online_game_store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.utmn.online_game_store.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmailIgnoreCase(@Param("email") String email);
}
