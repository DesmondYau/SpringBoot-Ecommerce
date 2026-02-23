package com.desmond.ecommerce.kafkaConsumer;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull (message = "Product Id is required")
        Integer id,
        @NotNull(message = "Purchase quantity is required")
        Integer quantity
) {
}