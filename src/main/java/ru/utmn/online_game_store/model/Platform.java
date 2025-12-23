package ru.utmn.online_game_store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Platform {
    PC("PC"),
    PS4("PS4"),
    PS5("PS5"),
    XBOX("XBOX");

    private final String value;
}
