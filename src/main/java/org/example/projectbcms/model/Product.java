package org.example.projectbcms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    @Column(name = "buy_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal buyPrice;

    @ManyToOne
    @JoinColumn(name = "product_line_id", nullable = false, updatable = false)
    private ProductLine productLine;
}
