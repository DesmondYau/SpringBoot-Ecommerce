package com.desmond.ecommerce.client;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
public interface ProductClient {
    @PostExchange("/purchase")
    public List<ProductPurchaseResponse> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> productPurchaseRequestList
    );
}
