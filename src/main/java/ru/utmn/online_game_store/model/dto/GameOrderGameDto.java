package ru.utmn.online_game_store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameOrderGameDto {
    private Integer id;
    private String title;
}