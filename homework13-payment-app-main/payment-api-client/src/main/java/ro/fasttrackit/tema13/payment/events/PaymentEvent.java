package ro.fasttrackit.tema13.payment.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import ro.fasttrackit.tema13.payment.domain.response.PaymentResponseDto;

@Value
@Builder
@JsonDeserialize(builder = PaymentEvent.PaymentEventBuilder.class)
public class PaymentEvent {
    PaymentResponseDto paymentResponseDto;
    PaymentEventEventType type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PaymentEventBuilder {
    }
}
