package ru.utmn.online_game_store.controller.external;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.utmn.online_game_store.model.dto.GameShopRequestBody;
import ru.utmn.online_game_store.model.dto.GameShopRecordDto;
import ru.utmn.online_game_store.model.external.GameShopRecord;
import ru.utmn.online_game_store.service.external.GameShopService;

@RestController
@RequestMapping("/external-api/v1/game-shop")
@Validated
public class GameShopController {
    @Autowired
    GameShopService gameShopService;

    @Operation(summary = "Возращает цифровые ключи игр для оплаченного заказа.")
    @PostMapping
    public ResponseEntity<GameShopRecordDto> getGameKeysForGameOrder(
            @Valid @RequestBody GameShopRequestBody body
    ) {
        GameShopRecord result = gameShopService.getGameKeysForGameOrder(body);

        return ResponseEntity.ok(gameShopService.castToGameShopKeyDto(result));
    }
}
