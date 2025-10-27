package com.desmond.ecommerce.order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal totalAmount,
        String customerId
) {
}
