package com.desmond.ecommerce.stock;

import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public StockResponse toStockRespsonse(Stock stock) {
       return new StockResponse(
               stock.getId(),
               stock.getQuantity()
       );
    }
}
