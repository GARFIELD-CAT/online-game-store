package ru.utmn.online_game_store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgeRating {
    THREE("3+"),
    SEVEN("7+"),
    TWELWE("12+"),
    SEXTEEN("16+"),
    EIGHTEEN("18+");

    private final String value;
}
