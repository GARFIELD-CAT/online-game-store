package ru.utmn.online_game_store.model.dto;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameShopRecordDto {
    private Integer id;
    private Integer order_id;

    private List<GameShopKeyDto> keys;
}
