package com.desmond.ecommerce.customer;


import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @CachePut(value = "customer", key="#result.id")
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toCustomerResponse).toList();
    }

    @Cacheable(value = "customer", key="#customerId")
    public CustomerResponse findCustomerById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException(
                                "No customer found with ID: " + customerId
                        )
                );
        return customerMapper.toCustomerResponse(customer);
    }

    public Boolean existById(Integer customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }


}
