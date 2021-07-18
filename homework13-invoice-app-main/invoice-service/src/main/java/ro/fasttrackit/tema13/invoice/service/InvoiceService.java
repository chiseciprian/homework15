package ro.fasttrackit.tema13.invoice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.tema13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.tema13.invoice.domain.request.InvoiceRequestDto;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;
import ro.fasttrackit.tema13.invoice.model.mappers.InvoiceMappers;
import ro.fasttrackit.tema13.invoice.repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository repository;
    private final InvoiceMappers mapper;
    private final InvoiceNotifications invoiceNotifications;

    public InvoiceEntity addInvoice(InvoiceRequestDto request) {
        InvoiceEntity invoiceEntity = mapper.requestToDb(request);
        InvoiceEntity dbInvoice = repository.save(invoiceEntity);
        invoiceNotifications.notifyInvoiceCreated(dbInvoice);
        return dbInvoice;
    }

    public List<InvoiceEntity> getInvoices() {
        return repository.findAll();
    }

    public Optional<InvoiceEntity> getInvoice(String studentId) {
        return repository.findById(studentId);
    }

    public InvoiceEntity updateInvoiceStatus(String invoiceId) {
        InvoiceEntity invoiceEntity = repository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice with id " + invoiceId + " is not found"));
        invoiceEntity.setPayed(true);
        return repository.save(invoiceEntity);
    }

    public Optional<InvoiceEntity> deleteInvoice(String invoiceId) {
        Optional<InvoiceEntity> invoice = repository.findById(invoiceId);
        invoice.ifPresent(this::deleteExistingInvoice);
        return invoice;
    }

    private void deleteExistingInvoice(InvoiceEntity invoiceEntity) {
        repository.delete(invoiceEntity);
    }
}
