package ru.utmn.online_game_store.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@Setter
public class GameOrderUpdateRequestBody {
    @Schema(requiredMode = REQUIRED,
            description = "Id заказа"
    )
    @NotNull(message = "Id заказа должен быть указан.")
    private Integer order_id;

    @Schema(description = "Статус заказа")
    private String status;

    @Schema(description = "Общая сумма заказа")
    private Double totalAmount;
}
