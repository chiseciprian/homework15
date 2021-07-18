package ro.fasttrackit.tema13.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.tema13.invoice.domain.response.InvoiceResponseDto;
import ro.fasttrackit.tema13.invoice.events.InvoiceEvent;
import ro.fasttrackit.tema13.payment.domain.request.PaymentRequestDto;
import ro.fasttrackit.tema13.payment.model.PaymentEntity;

import static ro.fasttrackit.tema13.invoice.events.InvoiceEventEventType.ADDED;

@Slf4j
@Component
@RequiredArgsConstructor
public class InvoiceEventListener {
    private final PaymentService paymentService;

    @RabbitListener(queues = "#{invoiceQueue.name}")
    void processInvoiceEvent(InvoiceEvent event) {
        if (event.getType() == ADDED) {
            PaymentRequestDto paymentRequestDto = cratePaymentRequestDto(event);
            PaymentEntity paymentEntity = paymentService.addPayment(paymentRequestDto);
            log.info("Payment Created: " + paymentEntity);
        }
    }

    private PaymentRequestDto cratePaymentRequestDto(InvoiceEvent event) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        InvoiceResponseDto invoiceResponseDto = event.getInvoiceResponseDto();
        paymentRequestDto.setInvoicedId(invoiceResponseDto.getId());
        paymentRequestDto.setAmountPayable(invoiceResponseDto.getAmount());
        paymentRequestDto.setReceiver(invoiceResponseDto.getReceiver());
        paymentRequestDto.setSender(invoiceResponseDto.getSender());
        return paymentRequestDto;
    }
}
