package ru.utmn.online_game_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.Game;
import ru.utmn.online_game_store.model.GameOrder;
import ru.utmn.online_game_store.model.OrderStatus;
import ru.utmn.online_game_store.model.User;
import ru.utmn.online_game_store.model.dto.GameOrderCreateRequestBody;
import ru.utmn.online_game_store.model.dto.GameOrderDto;
import ru.utmn.online_game_store.model.dto.GameOrderGameDto;
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

    public List<GameOrder> getAll() {
        return gameOrderRepository.findAll();
    }

    public GameOrder getOne(Integer id) {
        User user = jpaUserDetailsService.getCurrentUser();

        Optional<GameOrder> gameOrder = gameOrderRepository.findById(id);

        if (gameOrder.isPresent()){
            if (Objects.equals(user.getId(), gameOrder.get().getUser().getId())) {
                return gameOrder.get();
            } else {
                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN, String.format("Заказ с id=%s не принадлежит текущему пользователю", id)
                );
            }
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("Заказ с id=%s не существует", id)
            );
        }
    }

    public GameOrder create(GameOrderCreateRequestBody body) {
        User user = jpaUserDetailsService.getCurrentUser();
        Set<Game> games = new HashSet<>();

        for (Integer game_id : body.getGame_ids()) {
            Game game = gameService.getOne(game_id);
            games.add(game);
        }

        GameOrder order = new GameOrder();
        order.setGames(games.stream().toList());
        order.setUser(user);
        order.setTotalAmount(
                games.stream().mapToDouble(
                        Game::getPrice).sum()
        );

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
        String status = body.getStatus();

        if (status != null) {
            if (!OrderStatus.canTransitionTo(order.getStatus(), status))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        String.format("Текущий статус заказа: %s не может быть изменен на %s", order.getStatus(), status)
                );

            order.setStatus(status);
        }

        if (body.getTotalAmount() != null) {
            order.setTotalAmount(body.getTotalAmount());
        }

        gameOrderRepository.save(order);

        return order;
    }

    public List<GameOrder> getAllByCurrentUser() {
        User user = jpaUserDetailsService.getCurrentUser();
        return gameOrderRepository.findByUserId(user.getId());
    }

    public List<GameOrder> getAllByUserId(Integer userId) {
        return gameOrderRepository.findByUserId(userId);
    }

    public GameOrderDto castToDtoResponse(GameOrder order) {
        GameOrderDto dto = new GameOrderDto();
        List<GameOrderGameDto> games = new ArrayList<>();

        for (Game game : order.getGames()){
            GameOrderGameDto gameDto = new GameOrderGameDto();
            gameDto.setId(game.getId());
            gameDto.setTitle(game.getTitle());
            games.add(gameDto);
        }

        dto.setId(order.getId());
        dto.setCreated(order.getCreated());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setUser_id(order.getUser().getId());
        dto.setGames(games);

        return dto;
    }
}
