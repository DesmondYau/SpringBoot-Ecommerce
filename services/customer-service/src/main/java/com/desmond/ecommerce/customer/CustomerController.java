package com.desmond.ecommerce.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Integer customerId) {
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @GetMapping("/exist/{customerId}")
    public ResponseEntity<Boolean> existById(@PathVariable Integer customerId) {
        return ResponseEntity.ok(customerService.existById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }
}
