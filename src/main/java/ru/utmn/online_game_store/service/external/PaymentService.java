package ru.utmn.online_game_store.service.external;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.utmn.online_game_store.model.GameOrder;
import ru.utmn.online_game_store.model.OrderStatus;
import ru.utmn.online_game_store.model.dto.GameOrderUpdateRequestBody;
import ru.utmn.online_game_store.model.dto.PaymentRequestBody;
import ru.utmn.online_game_store.model.dto.PaymentStatusDto;
import ru.utmn.online_game_store.model.external.Payment;
import ru.utmn.online_game_store.repository.external.PaymentRepository;
import ru.utmn.online_game_store.service.GameOrderService;

import java.util.Optional;

//По сути Mock над внешним api сервиса оплаты и логика обновления статуса заказа
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private GameOrderService gameOrderService;
    private Integer counter = 0;

    public PaymentStatusDto getPaymentStatus(@Valid PaymentRequestBody body) {
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

        PaymentStatusDto status = new PaymentStatusDto();

        if (counter % 2 == 0) {
            status.setStatus(OrderStatus.FAILED.getValue());
            return status;
        }

        Payment newPayment = new Payment();
        newPayment.setOrder(order);
        paymentRepository.save(newPayment);

        // Обновление статуса заказа
        GameOrderUpdateRequestBody gameOrderUpdateRequestBody = new GameOrderUpdateRequestBody();
        gameOrderUpdateRequestBody.setOrder_id(order.getId());
        gameOrderUpdateRequestBody.setStatus(OrderStatus.PAID.getValue());
        gameOrderService.update(gameOrderUpdateRequestBody);

        status.setStatus(OrderStatus.PAID.getValue());
        return status;
    }
}
