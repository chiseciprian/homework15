package ro.fasttrackit.tema13.payment.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.tema13.payment.domain.request.PaymentRequestDto;
import ro.fasttrackit.tema13.payment.domain.response.PaymentResponseDto;
import ro.fasttrackit.tema13.payment.model.mappers.PaymentMappers;
import ro.fasttrackit.tema13.payment.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {
    private final PaymentService service;
    private final PaymentMappers mappers;

    @GetMapping
    List<PaymentResponseDto> getPayments() {
        return mappers.toResponseDtoList(service.getPayments());
    }

    @PostMapping
    PaymentResponseDto addPayment(@RequestBody PaymentRequestDto request) {
        return mappers.toResponseDto(service.addPayment(request));
    }

    @PatchMapping(path = "{paymentId}")
    PaymentResponseDto patchPayment(@PathVariable String paymentId, @RequestBody JsonPatch patch) {
        return mappers.toResponseDto(service.patchPayment(paymentId, patch));
    }
}
