package com.desmond.ecommerce.product;

import com.desmond.ecommerce.exception.ProductPurchaseException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.List;;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Integer createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    @Transactional
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> productPurchaseRequestList
    ) {
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(ProductPurchaseRequest productPurchaseRequest : productPurchaseRequestList) {
            Integer productPurchaseRequestId = productPurchaseRequest.id();
            if(!productRepository.existsById(productPurchaseRequestId)) {
                throw new ProductPurchaseException("Product does not exist for ID: " + productPurchaseRequestId);
            }

            double productPurchaseRequestQuantity = productPurchaseRequest.quantity();
            Product product = productRepository.findById(productPurchaseRequestId).get();
            double availableQuantity = product.getAvailableQuantity();
            if(availableQuantity < productPurchaseRequestQuantity) {
                throw new ProductPurchaseException("Insufficient stock for product with ID: " + productPurchaseRequestId);
            }

            product.setAvailableQuantity(availableQuantity - productPurchaseRequestQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productPurchaseRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException(
                        "No product found with id" + productId
                        )
                );
        return productMapper.toProductResponse(product);
    }


    public List<ProductResponse> findAlProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }
}
