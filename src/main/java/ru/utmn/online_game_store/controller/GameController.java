package ru.utmn.online_game_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.utmn.online_game_store.model.Game;
import ru.utmn.online_game_store.service.GameService;


@RestController
@RequestMapping("/api/v1/games")
@Validated
public class GameController {
    @Autowired
    private GameService gameService;

    @Operation(summary = "Возвращает одну запись по ее id")
    @GetMapping("/{id}")
    public Game getOne(
            @PathVariable("id") Integer id
    ) {
        return gameService.getOne(id);
    }

    @Operation(summary = "Создает новую запись")
    @PostMapping
    public ResponseEntity<Game> add(
            @Valid @RequestBody Game game
    ) {
        Game entity = gameService.create(game);

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @Operation(summary = "Возвращает все записи")
    @GetMapping
    public ResponseEntity<Object> getAll() {
        Iterable<Game> games = gameService.getAll();

        return ResponseEntity.ok(games);
    }
}
