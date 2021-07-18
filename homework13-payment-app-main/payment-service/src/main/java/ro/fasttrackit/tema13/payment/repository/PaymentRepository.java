package ro.fasttrackit.tema13.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.tema13.payment.model.PaymentEntity;

public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
}
