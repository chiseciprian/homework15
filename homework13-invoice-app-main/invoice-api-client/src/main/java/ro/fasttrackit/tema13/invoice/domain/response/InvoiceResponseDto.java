package ro.fasttrackit.tema13.invoice.domain.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = InvoiceResponseDto.InvoiceResponseDtoBuilder.class)
public class InvoiceResponseDto {
    private String id;
    private String description;
    private double amount;
    private String sender;
    private String receiver;
    private boolean payed;

    @JsonPOJOBuilder(withPrefix = "")
    public static class InvoiceResponseDtoBuilder {
    }
}
