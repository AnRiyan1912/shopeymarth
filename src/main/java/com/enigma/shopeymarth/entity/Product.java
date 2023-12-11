package com.enigma.shopeymarth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder(toBuilder = true)
@Table(name = "m_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;
    @Column(name = "description", length = 150, nullable = false)
    private String description;
    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrice;
}
