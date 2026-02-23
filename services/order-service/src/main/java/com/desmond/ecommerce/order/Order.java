package com.desmond.ecommerce.order;


import com.desmond.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique=true, nullable=false)
    private String reference;
    private BigDecimal totalAmount;
    private String customerId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines;
}
