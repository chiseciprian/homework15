package ro.fasttrackit.tema13.payment.domain.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = PaymentResponseDto.PaymentResponseDtoBuilder.class)
public class PaymentResponseDto {
    private String id;
    private String invoicedId;
    private PaymentStatus status;
    private double amountPayable;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PaymentResponseDtoBuilder {
    }
}
