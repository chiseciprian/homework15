package ro.fasttrackit.tema13.payment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fasttrackit.tema13.exceptions.ResourceNotFoundException;
import ro.fasttrackit.tema13.payment.domain.request.PaymentRequestDto;
import ro.fasttrackit.tema13.payment.model.PaymentEntity;
import ro.fasttrackit.tema13.payment.model.mappers.PaymentMappers;
import ro.fasttrackit.tema13.payment.repository.PaymentRepository;

import java.util.List;

import static ro.fasttrackit.tema13.payment.domain.response.PaymentStatus.DONE;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMappers mapper;
    private final ObjectMapper objectMapper;
    private final PaymentNotifications paymentNotifications;

    public List<PaymentEntity> getPayments() {
        return repository.findAll();
    }

    @Transactional
    public PaymentEntity addPayment(PaymentRequestDto request) {
        PaymentEntity paymentEntity = mapper.toEntity(request);
        return repository.save(paymentEntity);
    }

    @SneakyThrows
    public PaymentEntity patchPayment(String paymentId, JsonPatch patch) {
        PaymentEntity dbPayment = repository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find payment with id " + paymentId));

        JsonNode patchedProductJson = patch.apply(objectMapper.valueToTree(dbPayment));
        PaymentEntity patchedPayment = objectMapper.treeToValue(patchedProductJson, PaymentEntity.class);
        PaymentEntity paymentEntity = replacePayment(paymentId, patchedPayment);
        if (paymentEntity.getStatus().equals(DONE)) {
            paymentNotifications.notifyPaymentStatusUpdated(paymentEntity);
        }
        return patchedPayment;
    }

    public PaymentEntity replacePayment(String paymentId, PaymentEntity newPayment) {
        newPayment.setId(paymentId);
        PaymentEntity dbPayment = repository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find payment with id " + paymentId));
        copyPayment(newPayment, dbPayment);
        return repository.save(dbPayment);
    }

    private void copyPayment(PaymentEntity newPayment, PaymentEntity dbPayment) {
        dbPayment.setAmountPayable(newPayment.getAmountPayable());
        dbPayment.setInvoicedId(newPayment.getInvoicedId());
        dbPayment.setStatus(newPayment.getStatus());
    }
}
