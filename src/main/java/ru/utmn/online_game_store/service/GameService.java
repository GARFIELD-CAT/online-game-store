package ru.utmn.online_game_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.Game;
import ru.utmn.online_game_store.repository.GameRepository;

import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Iterable<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game getOne(Integer id) {
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()){
            return game.get();
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Игра с id=%s не существует", id)
            );
        }
    }

    public Game create(Game game) {
        if (gameRepository.existsById(game.getId()))
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, String.format("Игра с id=%s создана ранее", game.getId())
            );

        game.setId(null);

        return gameRepository.save(game);
    }
}
