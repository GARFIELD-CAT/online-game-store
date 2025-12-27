package ru.utmn.online_game_store.service.external;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.Game;
import ru.utmn.online_game_store.model.GameOrder;
import ru.utmn.online_game_store.model.OrderStatus;
import ru.utmn.online_game_store.model.dto.GameOrderDto;
import ru.utmn.online_game_store.model.dto.GameShopKeyDto;
import ru.utmn.online_game_store.model.dto.GameShopRecordDto;
import ru.utmn.online_game_store.model.dto.GameShopRequestBody;
import ru.utmn.online_game_store.model.external.GameShopKey;
import ru.utmn.online_game_store.model.external.GameShopRecord;
import ru.utmn.online_game_store.repository.external.GameShopKeysRepository;
import ru.utmn.online_game_store.repository.external.GameShopRecordRepository;
import ru.utmn.online_game_store.service.GameOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//По сути Mock над внешним api магазина
@Service
public class GameShopService {
    @Autowired
    private GameOrderService gameOrderService;
    @Autowired
    private GameShopRecordRepository gameShopRecordRepository;
    @Autowired
    private GameShopKeysRepository gameShopKeysRepository;

    public GameShopRecord getGameKeysForGameOrder(@Valid GameShopRequestBody body) {
        GameOrder order = gameOrderService.getOne(body.getOrder_id());

        // Должна быть проверка еще, что переданный заказ принадлжеит текущему пользователю
        if (!Objects.equals(order.getStatus(), OrderStatus.PAID.getValue())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format(
                            "Заказ с id=%s должен иметь статус \"PAID\" - оплачен. Текущий статус заказа: %s", order.getId(), order.getStatus()
                    )
            );
        }

        // Если уже были получены ключи для заказа, то отдаем их, а не создаем новые
        Optional<GameShopRecord> oldGameShopRecord = gameShopRecordRepository.findByOrderId(order.getId());

        if (oldGameShopRecord.isPresent()){
            return oldGameShopRecord.get();
        }

        // Создаем новые ключи
        GameShopRecord newGameShopRecord = new GameShopRecord();
        newGameShopRecord.setOrder(order);

        gameShopRecordRepository.save(newGameShopRecord);

        List<GameShopKey> gameShopKeys = new ArrayList<>();

        for (Game game : order.getGames()) {
            GameShopKey gameKey = new GameShopKey();
            gameKey.setGameId(game.getId());
            gameKey.setGameShopRecord(newGameShopRecord);
            gameShopKeys.add(gameKey);
        }

        gameShopKeysRepository.saveAll(gameShopKeys);
        newGameShopRecord.setKeys(gameShopKeys);
        gameShopRecordRepository.save(newGameShopRecord);

        return newGameShopRecord;
    }

    public GameShopRecordDto castToGameShopKeyDto(GameShopRecord gameShopRecord) {
        GameShopRecordDto dto = new GameShopRecordDto();
        List<GameShopKeyDto> gameKeys = new ArrayList<>();

        for (GameShopKey key : gameShopRecord.getKeys()){
            GameShopKeyDto gameShopKeyDto = new GameShopKeyDto();
            gameShopKeyDto.setId(key.getId());
            gameShopKeyDto.setGameId(key.getGameId());
            gameShopKeyDto.setGameKey(key.getGameKey());

            gameKeys.add(gameShopKeyDto);
        }

        dto.setId(gameShopRecord.getId());
        dto.setOrder_id(gameShopRecord.getOrder().getId());
        dto.setKeys(gameKeys);

        return dto;
    }
}
