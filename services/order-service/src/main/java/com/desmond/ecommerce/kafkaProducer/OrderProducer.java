package com.desmond.ecommerce.kafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Component
public class OrderProducer {
    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public OrderProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        logger.info("Sending order confirmation");
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(message);
        logger.info("Successfully sent order confirmation");
    }
}
