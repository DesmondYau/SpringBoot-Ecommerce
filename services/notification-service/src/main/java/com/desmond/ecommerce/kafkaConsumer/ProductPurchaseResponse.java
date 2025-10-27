package com.desmond.ecommerce.kafkaConsumer;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        String name,
        String description,
        double quantity,
        BigDecimal price
) {
}
