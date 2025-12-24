package ru.utmn.online_game_store.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@Setter
public class GameOrderCreateRequestBody {
    @Schema(requiredMode = REQUIRED,
            description = "Id пользователя"
    )
    @NotNull(message = "Id пользователя должен быть указан.")
    private Integer userId;

    @Schema(requiredMode = REQUIRED,
            description = "Список игр для заказа"
    )
    @Size(min = 1, message = "Хотя бы 1 игра должна быть указана.")
    @NotNull(message = "Хотя бы 1 игра должна быть указана.")
    private List<Integer> game_ids = new ArrayList<>();
}
