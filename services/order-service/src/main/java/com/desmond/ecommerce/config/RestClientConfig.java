package com.desmond.ecommerce.config;

import com.desmond.ecommerce.client.CustomerClient;
import com.desmond.ecommerce.client.ProductClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
    @Value("${application.config.customer-url}")
    private String customerServiceUrl;
    @Value("${application.config.product-url}")
    private String productServiceUrl;

    @Bean
    public CustomerClient customerClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(customerServiceUrl)
                .build();
        var restClientAdapater = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapater).build();
        return httpServiceProxyFactory.createClient(CustomerClient.class);
    }

    @Bean
    public ProductClient productClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(productServiceUrl)
                .build();
        var restClientAdapater = RestClientAdapter.create(restClient);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapater).build();
        return httpServiceProxyFactory.createClient(ProductClient.class);
    }
}