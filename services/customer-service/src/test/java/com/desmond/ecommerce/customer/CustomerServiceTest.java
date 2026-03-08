package com.desmond.ecommerce.customer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Customer Service Unit Tests")
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;        // This is want we want to test

    private CustomerRequest testCustomerRequest;
    private Customer testCustomer;
    private CustomerResponse testCustomerResponse;

    @BeforeEach
    void setup() {
        this.testCustomerRequest = new CustomerRequest("Desmond", "Test", "desmondtest@gmail.com");
        this.testCustomer = Customer.builder()
                .id(1)
                .firstName("Desmond")
                .lastName("Test")
                .email("desmondtest@gmail.com")
                .build();
        this.testCustomerResponse = new CustomerResponse(1, "Desmond", "Test", "desmondtest@gmail.com);");
    }

    @DisplayName("Create Customer Test")
    @Nested
    class CreateCustomerTest {

        @Test
        @DisplayName("Should create customer successfully")
        void shouldCreateCustomerSuccessfully() {
            // Given
            when(customerMapper.toCustomer(testCustomerRequest)).thenReturn(testCustomer);
            when(customerRepository.save(testCustomer)).thenReturn(testCustomer);
            when(customerMapper.toCustomerResponse(testCustomer)).thenReturn(testCustomerResponse);

            // When
            final CustomerResponse customerResponse = customerService.createCustomer(testCustomerRequest);

            // Then
            assertEquals(customerResponse, testCustomerResponse);
            verify(customerMapper, times(1)).toCustomer(testCustomerRequest);
            verify(customerRepository, times(1)).save(testCustomer);
            verify(customerMapper, times(1)).toCustomerResponse(testCustomer);
        }
    }
}