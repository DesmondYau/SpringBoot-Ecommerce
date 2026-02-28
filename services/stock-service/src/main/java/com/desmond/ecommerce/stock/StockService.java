package com.desmond.ecommerce.stock;


import com.desmond.ecommerce.kafkaConsumer.ProductPurchaseRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final RedisTemplate redisTemplate;
    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, RedisTemplate redisTemplate, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.redisTemplate = redisTemplate;
        this.stockMapper = stockMapper;
    }

    public void publishStockToCache(Integer productId) {
        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(
                                "No stock found with product id" + productId
                        )
                );
        redisTemplate.opsForValue().set("item_stock_" + stock.getId(), stock.getQuantity());
    }

    public List<StockResponse> getAllStock() {
        return stockRepository.findAll().stream().map(stockMapper::toStockRespsonse).toList();
    }

    @Transactional
    @KafkaListener(topics = "stock-topic")
    public void changeStock(ProductPurchaseRequest productPurchaseRequest) {
        int updated = stockRepository.decrementStock(productPurchaseRequest.id(), productPurchaseRequest.quantity());
        if (updated == 0) {
            throw new IllegalStateException("Stock update failed");
        }
    }
}
