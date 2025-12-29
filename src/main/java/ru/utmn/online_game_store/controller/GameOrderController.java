package ru.utmn.online_game_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.GameOrder;
import ru.utmn.online_game_store.model.dto.GameOrderCreateRequestBody;
import ru.utmn.online_game_store.model.dto.GameOrderDto;
import ru.utmn.online_game_store.model.dto.GameOrderUpdateRequestBody;
import ru.utmn.online_game_store.service.GameOrderService;

import java.util.ArrayList;
import java.util.List;

import static ru.utmn.online_game_store.Constants.ORDER_STATUSES;


@RestController
@RequestMapping("/api/v1/game-orders")
@Validated
public class GameOrderController {
    @Autowired
    private GameOrderService gameOrderService;

    @Operation(summary = "Возвращает одну запись по ее id")
    @GetMapping("/{id}")
    public GameOrderDto getOne(
            @PathVariable("id") Integer id
    ) {
        return gameOrderService.castToDtoResponse(gameOrderService.getOne(id));
    }

    @Operation(summary = "Создает новую запись")
    @PostMapping
    public ResponseEntity<GameOrderDto> add(
            @Valid @RequestBody GameOrderCreateRequestBody body
    ) {
        GameOrder entity = gameOrderService.create(body);

        return new ResponseEntity<>(gameOrderService.castToDtoResponse(entity), HttpStatus.CREATED);
    }

    @Operation(summary = "Возвращает все записи")
    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false) Integer userId
    ) {
        List<GameOrder> rawOrders;

        if (userId != null) {
            rawOrders = gameOrderService.getAllByUserId(userId);
        } else {
            rawOrders = gameOrderService.getAll();
        }

        List<GameOrderDto> gameOrders = new ArrayList<>(rawOrders.size());

        for (GameOrder order : rawOrders) {
            gameOrders.add(gameOrderService.castToDtoResponse(order));
        }

        return ResponseEntity.ok(gameOrders);
    }

    @Operation(summary = "Возвращает все записи для текущего пользователя")
    @GetMapping("for-current-user")
    public ResponseEntity<Object> getAllForCurrentUser() {
        List<GameOrder> rawOrders = gameOrderService.getAllByCurrentUser();

        List<GameOrderDto> gameOrders = new ArrayList<>(rawOrders.size());

        for (GameOrder order : rawOrders) {
            gameOrders.add(gameOrderService.castToDtoResponse(order));
        }

        return ResponseEntity.ok(gameOrders);
    }

    @Operation(summary = "Удаляет одну запись по ее id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Integer id
    ) {
        gameOrderService.delete(id);
    }

    @Operation(summary = "Обновляет одну запись по ее id")
    @PutMapping
    public ResponseEntity<Object> update(
            @Valid @RequestBody GameOrderUpdateRequestBody body
    ) {
        if (body.getTotalAmount() != null && body.getTotalAmount() < 0) {
            return ResponseEntity.badRequest().body("Сумма заказа не может быть меньше 0");
        }

        String status = body.getStatus();

        if (status != null) {
            if (!ORDER_STATUSES.contains(status)) {
                throw  new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        String.format("Заказ может содержать только такие статусы: %s, %s, %s и %s", ORDER_STATUSES.toArray())
                );
            }
        }

        GameOrder entity = gameOrderService.update(body);

        return new ResponseEntity<>(gameOrderService.castToDtoResponse(entity), HttpStatus.OK);
    }
}
