package com.desmond.ecommerce.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private StockMapper stockMapper;

    public ProductMapper(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    public Product toProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        Stock stock = stockMapper.toStock(productRequest);
        stock.setProduct(product); // owning side
        product.setStock(stock); // inverse sid
        return product;
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }


    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Integer quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                quantity,
                product.getPrice()
        );
    }
}
