package ro.fasttrackit.tema13.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.tema13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.tema13.invoice.domain.request.InvoiceRequestDto;
import ro.fasttrackit.tema13.invoice.domain.response.InvoiceResponseDto;
import ro.fasttrackit.tema13.invoice.model.mappers.InvoiceMappers;
import ro.fasttrackit.tema13.invoice.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@CrossOrigin
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final InvoiceMappers mapper;

    @GetMapping
    List<InvoiceResponseDto> getInvoices() {
        return mapper.toResponseDtoList(invoiceService.getInvoices());
    }

    @GetMapping(path = "{invoiceId}")
    InvoiceResponseDto getInvoice(@PathVariable String invoiceId) {
        return invoiceService.getInvoice(invoiceId)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice with id " + invoiceId + " is not found"));
    }

    @PostMapping
    InvoiceResponseDto addInvoice(@RequestBody InvoiceRequestDto request) {
        return mapper.toResponseDto(invoiceService.addInvoice(request));
    }

    @DeleteMapping("{invoiceId}")
    InvoiceResponseDto deleteInvoice(@PathVariable String invoiceId) {
        return invoiceService.deleteInvoice(invoiceId)
                .map(mapper::toResponseDto)
                .orElse(null);
    }
}
