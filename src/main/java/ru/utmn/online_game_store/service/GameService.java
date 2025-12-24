package ru.utmn.online_game_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.Game;
import ru.utmn.online_game_store.model.dto.GameDto;
import ru.utmn.online_game_store.repository.GameRepository;


import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAll() {
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


    public GameDto castToDtoResponse(Game game) {
        GameDto dto = new GameDto();

        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setDescription(game.getDescription());
        dto.setGenre(game.getGenre());
        dto.setPrice(game.getPrice());
        dto.setDeveloperName(game.getDeveloperName());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setPlatform(game.getPlatform());
        dto.setIsAvailable(game.getIsAvailable());
        dto.setAverageUserRating(game.getAverageUserRating());
        dto.setAgeRating(game.getAgeRating());

        return dto;
    }

    public Game castFromDtoRequest(GameDto gameDto) {
        Game game = new Game();

        game.setId(gameDto.getId());
        game.setTitle(gameDto.getTitle());
        game.setDescription(gameDto.getDescription());
        game.setGenre(gameDto.getGenre());
        game.setPrice(gameDto.getPrice());
        game.setDeveloperName(gameDto.getDeveloperName());
        game.setReleaseDate(gameDto.getReleaseDate());
        game.setPlatform(gameDto.getPlatform());
        game.setIsAvailable(gameDto.getIsAvailable());
        game.setAverageUserRating(gameDto.getAverageUserRating());
        game.setAgeRating(gameDto.getAgeRating());

        return game;
    }
}
