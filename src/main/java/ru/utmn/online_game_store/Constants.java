package ru.utmn.online_game_store;

import ru.utmn.online_game_store.model.OrderStatus;

import java.util.Arrays;
import java.util.List;

public class Constants {
        public static final List<String> ORDER_STATUSES = Arrays.asList(
                OrderStatus.PENDING.getValue(),
                OrderStatus.PAID.getValue(),
                OrderStatus.SUCCESS.getValue(),
                OrderStatus.FAILED.getValue()
        );
}
