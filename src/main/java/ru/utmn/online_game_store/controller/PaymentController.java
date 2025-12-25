package ru.utmn.online_game_store.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.utmn.online_game_store.model.dto.PaymentRequestBody;
import ru.utmn.online_game_store.service.PaymentService;

//По сути Mock над внешним сервисом оплаты
@RestController
@RequestMapping("/external-api/v1/payments")
@Validated
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Передает информацию об оплате в платежную систему и получает статус оплаты")
    @PostMapping
    public ResponseEntity<Object> getPaymentStatus(
            @Valid @RequestBody PaymentRequestBody body
    ) {
        return ResponseEntity.ok(paymentService.getPaymentStatus(body));
    }
}