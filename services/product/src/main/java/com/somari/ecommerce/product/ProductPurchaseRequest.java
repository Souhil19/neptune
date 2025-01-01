package com.somari.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product ID is required")
        Integer productId,
        @NotNull(message = "Quantity is required")
        double quantity
) {
}
