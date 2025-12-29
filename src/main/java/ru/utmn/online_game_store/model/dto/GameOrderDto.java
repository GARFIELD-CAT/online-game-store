package ru.utmn.online_game_store.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameOrderDto {
    private Integer id;
    private LocalDateTime created;
    private String status;
    private double totalAmount;
    private Integer user_id;
    private List<GameOrderGameDto> games;
}
