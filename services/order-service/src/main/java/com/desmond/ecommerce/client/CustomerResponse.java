package com.desmond.ecommerce.client;



public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {

}