package com.desmond.ecommerce.exception;

import com.desmond.ecommerce.product.ProductPurchaseRequest;

public class ProductPurchaseException extends RuntimeException{
    public ProductPurchaseException(String s) {
        super(s);
    }
}
