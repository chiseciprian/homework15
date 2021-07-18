package ro.fasttrackit.tema13.invoice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.tema13.invoice.model.InvoiceEntity;

public interface InvoiceRepository extends MongoRepository<InvoiceEntity, String> {
}
