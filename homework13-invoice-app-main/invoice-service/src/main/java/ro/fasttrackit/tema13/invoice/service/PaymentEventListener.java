package ro.fasttrackit.tema13.invoice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;
import ro.fasttrackit.tema13.payment.events.PaymentEvent;

import static ro.fasttrackit.tema13.payment.events.PaymentEventEventType.UPDATED;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {
    private final InvoiceService invoiceService;

    @RabbitListener(queues = "#{paymentQueue.name}")
    void processInvoiceEvent(PaymentEvent event) {
        if (event.getType() == UPDATED) {
            InvoiceEntity invoiceEntity = invoiceService.updateInvoiceStatus(event.getPaymentResponseDto().getInvoicedId());
            log.info("Invoice Updated: " + invoiceEntity);
        }
    }
}
