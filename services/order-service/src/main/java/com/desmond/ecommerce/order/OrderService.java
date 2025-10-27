package com.desmond.ecommerce.order;

import com.desmond.ecommerce.client.CustomerClient;
import com.desmond.ecommerce.client.CustomerResponse;
import com.desmond.ecommerce.client.ProductClient;
import com.desmond.ecommerce.client.ProductPurchaseResponse;
import com.desmond.ecommerce.kafkaProducer.OrderConfirmation;
import com.desmond.ecommerce.kafkaProducer.OrderProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper,
                        CustomerClient customerClient, ProductClient productClient, OrderProducer orderProducer) {
         this.orderRepository = orderRepository;
         this.orderMapper = orderMapper;
         this.customerClient = customerClient;
         this.productClient = productClient;
         this.orderProducer = orderProducer;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.customerLookupRequest());

        List<ProductPurchaseResponse> productPurchaseResponseList = productClient.purchaseProducts(
                orderRequest.productPurchaseRequestList());

        Order order = orderRepository.save(orderMapper.toOrder(orderRequest));

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                customerResponse.email(),
                order.getReference(),
                order.getTotalAmount(),
                productPurchaseResponseList
        ));

        return orderMapper.toOrderResponse(order);
    }

    public List<OrderResponse> findallOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

}
