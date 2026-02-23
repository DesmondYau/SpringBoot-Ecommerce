package com.desmond.ecommerce.kafkaConsumer;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String email,
        String orderReference,
        BigDecimal totalAmount,
        List<ProductPurchaseResponse> productPurchaseResponseList
) {
}
