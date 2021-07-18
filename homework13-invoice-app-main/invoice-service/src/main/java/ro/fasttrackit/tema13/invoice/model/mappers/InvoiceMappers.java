package ro.fasttrackit.tema13.invoice.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.tema13.invoice.domain.request.InvoiceRequestDto;
import ro.fasttrackit.tema13.invoice.domain.response.InvoiceResponseDto;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class InvoiceMappers {

    public InvoiceEntity requestToDb(InvoiceRequestDto request) {
        return InvoiceEntity.builder()
                .description(request.getDescription())
                .amount(request.getAmount())
                .sender(request.getSender())
                .receiver(request.getReceiver())
                .payed(false)
                .build();
    }

    public InvoiceResponseDto toResponseDto(InvoiceEntity addInvoice) {
        return InvoiceResponseDto.builder()
                .id(addInvoice.getId())
                .description(addInvoice.getDescription())
                .amount(addInvoice.getAmount())
                .sender(addInvoice.getSender())
                .receiver(addInvoice.getReceiver())
                .payed(addInvoice.isPayed())
                .build();
    }

    public List<InvoiceResponseDto> toResponseDtoList(List<InvoiceEntity> invoices) {
        return invoices.stream()
                .map(this::toResponseDto)
                .collect(toList());
    }
}
