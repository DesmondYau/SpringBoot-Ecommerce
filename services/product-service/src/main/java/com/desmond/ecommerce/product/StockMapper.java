package com.desmond.ecommerce.product;

import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public Stock toStock(ProductRequest productRequest) {
        Stock stock = Stock.builder()
                .quantity(productRequest.stock())
                .build();

        return stock;
    }
}
