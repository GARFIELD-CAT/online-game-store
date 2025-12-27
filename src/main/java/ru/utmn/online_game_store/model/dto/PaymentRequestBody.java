package ru.utmn.online_game_store.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

//По сути Mock над внешним сервисом оплаты
@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestBody {
    @Schema(requiredMode = REQUIRED,
            description = "Номер карты"
    )
    @NotNull(message = "Номер карты должен быть указан.")
    private String cardNumber;

    @Schema(requiredMode = REQUIRED,
            description = "Срок действия карты в формате mm/yy"
    )
    @NotNull(message = "Срок действия карты должен быть указан.")
    private String cardValidityPeriod;

    @Schema(requiredMode = REQUIRED,
            description = "CVC код карты"
    )
    @NotNull(message = "CVC код карты должен быть указан.")
    @Size(min = 3, message = "CVC код карты состоит из 3 цифр")
    private String cvcCode;

    @Schema(description = "Дата оплаты")
    private LocalDateTime paymentDate;

    @Schema(requiredMode = REQUIRED,
            description = "Имя и фамилия держателя карты"
    )
    @NotNull(message = "Имя и фамилия держателя карты должены быть указаны.")
    private String cartHolderName;

    @Schema(requiredMode = REQUIRED,
            description = "Id заказа"
    )
    @NotNull(message = "Id заказа должен быть указан.")
    private Integer order_id;

    @Schema(description = "Описание")
    private String description;
}
