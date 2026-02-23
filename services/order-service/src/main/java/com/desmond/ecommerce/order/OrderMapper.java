package com.desmond.ecommerce.order;

import com.desmond.ecommerce.orderline.OrderLine;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest, BigDecimal totalAmount) {
        Order order = Order.builder()
                .reference("ORD-" + LocalDate.now() + "-" + UUID.randomUUID().toString().substring(0, 8))
                .totalAmount(totalAmount)
                .customerId(orderRequest.customerLookupRequest().id())
                .orderLines(new ArrayList<OrderLine>())
                .build();

        List<OrderLine> orderLines = orderRequest.productPurchaseRequestList().stream()
                .map(productPurchaseRequest -> OrderLine.builder()
                        .order(order)
                        .productId(productPurchaseRequest.id())
                        .quantity(productPurchaseRequest.quantity())
                        .build())
                .toList();
        order.setOrderLines(orderLines);
        return order;
    }

    public OrderResponse toOrderResponse (Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getCustomerId()
        );
    }
}