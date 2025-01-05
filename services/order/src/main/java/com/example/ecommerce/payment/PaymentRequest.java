package com.example.ecommerce.payment;

import com.example.ecommerce.customer.CustomerResponse;
import com.example.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
