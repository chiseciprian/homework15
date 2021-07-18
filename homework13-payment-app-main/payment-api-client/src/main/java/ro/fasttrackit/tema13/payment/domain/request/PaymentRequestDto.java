package ro.fasttrackit.tema13.payment.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    private String invoicedId;
    private double amountPayable;
    private String receiver;
    private String sender;
}
