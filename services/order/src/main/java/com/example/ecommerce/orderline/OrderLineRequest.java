package com.example.ecommerce.orderline;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        @NotNull(message = "Product is required")
        Integer productId,
        @Positive(message = "Quantity should be positive")
        double quantity) {
}
