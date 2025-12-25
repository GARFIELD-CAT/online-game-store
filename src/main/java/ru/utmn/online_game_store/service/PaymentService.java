package ru.utmn.online_game_store.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.GameOrder;
import ru.utmn.online_game_store.model.OrderStatus;
import ru.utmn.online_game_store.model.dto.PaymentRequestBody;
import ru.utmn.online_game_store.model.external.Payment;
import ru.utmn.online_game_store.repository.PaymentRepository;

import java.util.Optional;

//По сути Mock над внешним сервисом оплаты
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private GameOrderService gameOrderService;
    private Integer counter = 0;

    public String getPaymentStatus(@Valid PaymentRequestBody body) {
        // Принимает данные оплаты и возвращает результат. Оплачен заказ или нет.
        // Для теста каждый четный запрос будет отклонен.
        counter += 1;

        GameOrder order = gameOrderService.getOne(body.getOrder_id());

        Optional<Payment> payment = paymentRepository.findByOrderId(order.getId());

        if (payment.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format("Заказ с id=%s уже оплачен.", order.getId())
            );
        }

        if (counter % 2 == 0) {
            return OrderStatus.FAILED.getValue();
        }

        Payment newPayment = new Payment();
        newPayment.setOrder(order);
        paymentRepository.save(newPayment);

        return OrderStatus.PAID.getValue();
    }
}
