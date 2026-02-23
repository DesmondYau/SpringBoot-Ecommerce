package com.desmond.ecommerce.kafkaProducer;

import com.desmond.ecommerce.client.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;


public record OrderConfirmation(
        String email,
        String orderReference,
        BigDecimal totalAmount,
        List<ProductPurchaseResponse> productPurchaseResponseList
) {
}
