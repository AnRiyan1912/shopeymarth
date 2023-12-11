package com.enigma.shopeymarth.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "m_product_price")
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "stock", columnDefinition = "int check (stock > 0)")
    private Integer stock;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "price", columnDefinition = "bigint check (price > 0)")
    private Long price;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
}
