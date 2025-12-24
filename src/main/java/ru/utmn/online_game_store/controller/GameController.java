package ru.utmn.online_game_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.utmn.online_game_store.model.Game;
import ru.utmn.online_game_store.model.dto.GameDto;
import ru.utmn.online_game_store.service.GameService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/games")
@Validated
public class GameController {
    @Autowired
    private GameService gameService;

    @Operation(summary = "Возвращает одну запись по ее id")
    @GetMapping("/{id}")
    public GameDto getOne(
            @PathVariable("id") Integer id
    ) {
        return gameService.castToDtoResponse(gameService.getOne(id));
    }

    @Operation(summary = "Создает новую запись")
    @PostMapping
    public ResponseEntity<GameDto> add(
            @Valid @RequestBody GameDto game
    ) {
        Game entity = gameService.create(gameService.castFromDtoRequest(game));

        return new ResponseEntity<>(gameService.castToDtoResponse(entity), HttpStatus.CREATED);
    }

    @Operation(summary = "Возвращает все записи")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Игры успешно получены",
                    content = @Content(schema = @Schema(implementation = GameDto.class))
            ),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Game> rawGames = gameService.getAll();
        List<GameDto> games = new ArrayList<>(rawGames.size());

        for (Game game : rawGames) {
            games.add(gameService.castToDtoResponse(game));
        }

        return ResponseEntity.ok(games);
    }
}
