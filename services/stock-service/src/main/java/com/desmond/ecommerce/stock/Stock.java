package com.desmond.ecommerce.stock;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "stock")
public class Stock {
    @Id
    private Integer id;
    private Integer quantity;
}