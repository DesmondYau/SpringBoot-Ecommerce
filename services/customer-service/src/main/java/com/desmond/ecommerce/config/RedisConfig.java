package com.desmond.ecommerce.config;


import com.desmond.ecommerce.customer.CustomerRepository;
import com.desmond.ecommerce.customer.CustomerResponse;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@EnableCaching
@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        // Value Serializer for single CustomerResponse
        Jackson2JsonRedisSerializer<CustomerResponse> customerSerializer =
                new Jackson2JsonRedisSerializer<>(CustomerResponse.class);
        // RedisCacheConfiguration for single CustomerResponse
        RedisCacheConfiguration customerConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(customerSerializer))
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues();

        Map<String, RedisCacheConfiguration> configs = new HashMap<>();
        configs.put("customer", customerConfig);

        // Set up RedisCacheManager with above configurations
        return  RedisCacheManager.builder(connectionFactory)
                .withInitialCacheConfigurations(configs)
                .build();

    }

}
