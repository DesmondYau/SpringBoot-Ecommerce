package com.desmond.ecommerce.client;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface CustomerClient {

    @PostExchange("/find")
    public CustomerResponse findCustomerById(@RequestBody CustomerLookupRequest customerLookupRequest);
}
