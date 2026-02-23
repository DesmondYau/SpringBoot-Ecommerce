package com.desmond.ecommerce.product;


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

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Product product;
}
