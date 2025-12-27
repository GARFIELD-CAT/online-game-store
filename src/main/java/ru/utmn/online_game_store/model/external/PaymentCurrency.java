package ru.utmn.online_game_store.model.external;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentCurrency {
    RUB("RUB"), // Российские рубли
    USD("USD"), // Доллар США
    EUR("EUR"); // Евро

    private final String value;
}
