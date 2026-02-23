package com.desmond.ecommerce.client;

import jakarta.validation.constraints.NotNull;

public record CustomerLookupRequest(
        @NotNull
        String id
) {
}
