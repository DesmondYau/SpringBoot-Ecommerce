package com.desmond.ecommerce.stock;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/publishStock/{productId}")
    public ResponseEntity<String> publishStock(@PathVariable Integer productId) {
        stockService.publishStockToCache(productId);
        return ResponseEntity.ok("Stock for product " + productId + " published to cache.");
    }

    @GetMapping
    public ResponseEntity<List<StockResponse>> getAllStock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }
}
