package com.desmond.ecommerce.kafkaProducer;

import com.desmond.ecommerce.product.ProductPurchaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
public class StockProducer {
    private static final Logger logger = LoggerFactory.getLogger(StockProducer.class);
    private final KafkaTemplate<String, ProductPurchaseRequest> kafkaTemplate;
    private final RedisTemplate redisTemplate;

    public StockProducer(KafkaTemplate kafkaTemplate, RedisTemplate redisTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.redisTemplate = redisTemplate;
    }

    public void sendStockChange(ProductPurchaseRequest productPurchaseRequest) {
        logger.info("Sending stock change to database");
        Message<ProductPurchaseRequest> message = MessageBuilder
                .withPayload(productPurchaseRequest)
                .setHeader(TOPIC, "stock-topic")
                .build();

        Integer productId = productPurchaseRequest.id();
        String stockKey = "item_stock_" + productId;
        Integer requestedQuantity = productPurchaseRequest.quantity();
        kafkaTemplate.send(message)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        logger.error("Failed to send stock change for product {}", productId);
                        redisTemplate.opsForValue().increment(stockKey, requestedQuantity);
                    } else {
                        logger.info("Successfully sent stock change for product {}", productId); }
                }
        );
    }
}
