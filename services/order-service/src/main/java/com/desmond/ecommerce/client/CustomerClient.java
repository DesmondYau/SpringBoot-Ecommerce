package com.desmond.ecommerce.client;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface CustomerClient {

    @GetExchange("/{customerId}")
    public CustomerResponse findCustomerById(@PathVariable("customerId") String customerId);
}
