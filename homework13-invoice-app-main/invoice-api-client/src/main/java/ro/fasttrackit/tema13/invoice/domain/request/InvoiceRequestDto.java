package ro.fasttrackit.tema13.invoice.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceRequestDto {
    private String description;
    private double amount;
    private String sender;
    private String receiver;
}
