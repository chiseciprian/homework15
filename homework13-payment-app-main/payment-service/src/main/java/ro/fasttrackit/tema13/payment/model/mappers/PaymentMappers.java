package ro.fasttrackit.tema13.payment.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.tema13.payment.domain.request.PaymentRequestDto;
import ro.fasttrackit.tema13.payment.domain.response.PaymentResponseDto;
import ro.fasttrackit.tema13.payment.model.PaymentEntity;

import java.util.List;
import java.util.stream.Collectors;

import static ro.fasttrackit.tema13.payment.domain.response.PaymentStatus.PENDING;
import static ro.fasttrackit.tema13.payment.domain.response.PaymentStatus.REJECT;

@Component
public class PaymentMappers {
    public List<PaymentResponseDto> toResponseDtoList(List<PaymentEntity> payments) {
        return payments.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public PaymentResponseDto toResponseDto(PaymentEntity paymentEntity) {
        return PaymentResponseDto.builder()
                .id(paymentEntity.getId())
                .invoicedId(paymentEntity.getInvoicedId())
                .status(paymentEntity.getStatus())
                .amountPayable(paymentEntity.getAmountPayable())
                .build();
    }

    public PaymentEntity toEntity(PaymentRequestDto request) {
        PaymentEntity entity = PaymentEntity.builder()
                .invoicedId(request.getInvoicedId())
                .amountPayable(request.getAmountPayable())
                .build();
        if (request.getSender() == null || request.getReceiver() == null) {
            entity.setStatus(REJECT);
        } else {
            entity.setStatus(PENDING);
        }
        return entity;
    }
}
