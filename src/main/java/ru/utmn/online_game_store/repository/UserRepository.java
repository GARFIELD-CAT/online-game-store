package ru.utmn.online_game_store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.utmn.online_game_store.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmailIgnoreCase(@Param("email") String email);
}
