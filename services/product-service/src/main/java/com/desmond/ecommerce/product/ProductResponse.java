package com.desmond.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price
) {
}
