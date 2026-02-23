package com.desmond.ecommerce.order;


import com.desmond.ecommerce.client.CustomerLookupRequest;
import com.desmond.ecommerce.client.ProductPurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        @NotBlank(message = "Customer Id is required")
        CustomerLookupRequest customerLookupRequest,
        @NotEmpty(message = "Order should contain at least one product")
        List<ProductPurchaseRequest> productPurchaseRequestList
) {
}
