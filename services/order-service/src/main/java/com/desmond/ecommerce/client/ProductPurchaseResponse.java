package com.desmond.ecommerce.client;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer id,
        String name,
        String description,
        double quantity,
        BigDecimal price
) {
}
