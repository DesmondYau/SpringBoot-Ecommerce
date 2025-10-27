package com.desmond.ecommerce.customer;

import ch.qos.logback.core.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
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

    public String createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new NoSuchElementException(
                        "No customer found with ID: " + customerRequest.id()
                        )
                );
        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setEmail(customerRequest.email());
        }
        customerRepository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::fromCustomer).toList();
    }

    public CustomerResponse findCustomerById(CustomerLookupRequest customerLookupRequest) {
        Customer customer = customerRepository.findById(customerLookupRequest.id())
                .orElseThrow(() -> new NoSuchElementException(
                                "No customer found with ID: " + customerLookupRequest.id()
                        )
                );
        return customerMapper.fromCustomer(customer);
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);
    }


}
