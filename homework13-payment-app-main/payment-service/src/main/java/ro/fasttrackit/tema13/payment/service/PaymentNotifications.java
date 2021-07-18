package ro.fasttrackit.tema13.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.tema13.payment.events.PaymentEvent;
import ro.fasttrackit.tema13.payment.model.PaymentEntity;
import ro.fasttrackit.tema13.payment.model.mappers.PaymentMappers;

import static ro.fasttrackit.tema13.payment.events.PaymentEventEventType.UPDATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentNotifications {
    private final RabbitTemplate rabbit;
    private final PaymentMappers mapper;
    private final FanoutExchange paymentExchange;

    public void notifyPaymentStatusUpdated(PaymentEntity paymentEntity) {
        PaymentEvent event = PaymentEvent.builder()
                .paymentResponseDto(mapper.toResponseDto(paymentEntity))
                .type(UPDATED)
                .build();
        log.info("Sending event : " + event);
        rabbit.convertAndSend(paymentExchange.getName(), "", event);
    }
}
