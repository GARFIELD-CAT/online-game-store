package ru.utmn.online_game_store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING("PENDING"), // Заказ создан. Ожидает оплаты
    PAID("PAID"), // Заказ оплачен
    SUCCESS("SUCCESS"), // Заказ успешно завершен
    FAILED("FAILED"); // Заказ завершен с ошибкой

    private final String value;

    private static final Map<String, List<String>> allowedTransitions = Map.of(
            PENDING.value, List.of(PAID.value, FAILED.value),
            PAID.value, List.of(SUCCESS.value, FAILED.value),
            SUCCESS.value, List.of(),
            FAILED.value, List.of()
    );

    public static boolean canTransitionTo(String oldStatus, String newStatus) {
        return allowedTransitions.get(oldStatus).contains(newStatus);
    }
}
