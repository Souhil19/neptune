package com.example.ecommerce.order;

import com.example.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount should be positive")

        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer is required")
        @NotEmpty(message = "Customer is required")
        @NotBlank(message = "Customer is required")
        String customerId,
        @NotEmpty(message = "Products are required")
        List<PurchaseRequest> products
) {
}
