package com.somari.ecommerce.kafka.payment;

import org.springframework.messaging.simp.stomp.StompReactorNettyCodec;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
