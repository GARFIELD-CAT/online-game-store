package ru.utmn.online_game_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.Game;
import ru.utmn.online_game_store.model.GameOrder;
import ru.utmn.online_game_store.model.User;
import ru.utmn.online_game_store.model.dto.GameOrderCreateRequestBody;
import ru.utmn.online_game_store.model.dto.GameOrderUpdateRequestBody;
import ru.utmn.online_game_store.repository.GameOrderRepository;

import java.util.*;

@Service
public class GameOrderService {
    @Autowired
    private GameOrderRepository gameOrderRepository;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private GameService gameService;

    public Iterable<GameOrder> getAll() {
        return gameOrderRepository.findAll();
    }

    public GameOrder getOne(Integer id) {
        Optional<GameOrder> gameOrder = gameOrderRepository.findById(id);

        if (gameOrder.isPresent()){
            return gameOrder.get();
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Заказ с id=%s не существует", id)
            );
        }
    }

    public GameOrder create(GameOrderCreateRequestBody body) {
        User user = jpaUserDetailsService.getOne(body.getUserId());
        Set<Game> games = new HashSet<>();
        double totalAmount = 0;

        for (Integer game_id : body.getGame_ids()) {
            Game game = gameService.getOne(game_id);
            games.add(game);
            totalAmount += game.getPrice();
        }

        GameOrder order = new GameOrder();
        order.setGames(games.stream().toList());
        order.setUser(user);
        order.setTotalAmount(totalAmount);

        return gameOrderRepository.save(order);
    }

    public void delete(Integer id) {
        if (!gameOrderRepository.existsById(id))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Заказ с id=%s не существует", id)
            );

        gameOrderRepository.deleteById(id);
    }

    public GameOrder update(GameOrderUpdateRequestBody body) {
        GameOrder order = gameOrderRepository.findById(body.getOrder_id()).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Заказ с id=%d не существует", body.getOrder_id())
                )
        );

        if (body.getStatus() != null) {
            order.setStatus(body.getStatus());
        }

        if (body.getTotalAmount() != null) {
            order.setTotalAmount(body.getTotalAmount());
        }

        gameOrderRepository.save(order);

        return order;
    }
}
