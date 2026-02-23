package com.desmond.ecommerce.product;

import com.desmond.ecommerce.exception.ProductPurchaseException;
import com.desmond.ecommerce.kafkaProducer.StockProducer;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final RedisTemplate redisTemplate;
    private final StockProducer stockProducer;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                          StockRepository stockRepository, RedisTemplate redisTemplate, StockProducer stockProducer) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.redisTemplate = redisTemplate;
        this.stockProducer = stockProducer;
    }


    @CachePut(value = "product", key="#result.productId")
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }

    @Cacheable(value = "product", key="#productId")
    public ProductResponse findProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(
                                "No product found with id" + productId
                        )
                );
        return productMapper.toProductResponse(product);
    }


    @Transactional
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> productPurchaseRequestList
    ) {
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(ProductPurchaseRequest productPurchaseRequest : productPurchaseRequestList) {
            Integer productId = productPurchaseRequest.id();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductPurchaseException(
                            "Product does not exist for ID: " + productId
                    ));

            Integer requestedQuantity = productPurchaseRequest.quantity();

            String stockKey = "item_stock_" + productId;
            Integer currentStock = (Integer) redisTemplate.opsForValue().get(stockKey);
            if (currentStock == null) {
                throw new ProductPurchaseException("Stock not cached for product ID: " + productId);
            }


            // 1. Post-check after atomic decrement
            Long remainingStock = redisTemplate.opsForValue().decrement(stockKey, requestedQuantity);
            if (remainingStock < 0) {
                redisTemplate.opsForValue().increment(stockKey, requestedQuantity);
                throw new ProductPurchaseException("Concurrent purchase caused insufficient stock for product ID: " + productId);
            }
            // 2. Send stock change to kafka to update database asynchronously
            stockProducer.sendStockChange(productPurchaseRequest);

            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productPurchaseRequest.quantity()));
        }
        return purchasedProducts;
    }
}
