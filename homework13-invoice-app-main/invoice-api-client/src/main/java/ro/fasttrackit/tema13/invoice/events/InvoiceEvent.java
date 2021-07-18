package ro.fasttrackit.tema13.invoice.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import ro.fasttrackit.tema13.invoice.domain.response.InvoiceResponseDto;

@Value
@Builder
@JsonDeserialize(builder = InvoiceEvent.InvoiceEventBuilder.class)
public class InvoiceEvent {
    InvoiceResponseDto invoiceResponseDto;
    InvoiceEventEventType type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class InvoiceEventBuilder {
    }
}
