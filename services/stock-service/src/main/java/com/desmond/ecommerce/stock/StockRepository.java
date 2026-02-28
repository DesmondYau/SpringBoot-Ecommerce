package com.desmond.ecommerce.stock;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Modifying
    @Query("Update Stock s SET s.quantity = s.quantity - :quantity WHERE s.id = :id")
    int decrementStock(@Param("id") Integer id, @Param("quantity") Integer quantity);
}
