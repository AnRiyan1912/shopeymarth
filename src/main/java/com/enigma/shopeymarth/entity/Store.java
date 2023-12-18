package com.enigma.shopeymarth.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "m_store")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "no_siup", unique = true, nullable = false, length = 30)
    private String noSiup;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "address", nullable = false, length = 100)
    private String address;
    @Column(name = "mobile_phone", unique = true, nullable = false, length = 30)
    private String mobilePhone;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "store")
    private List<ProductPrice> productPrice;

    public Store(String id, String noSiup, String name, String address, String mobilePhone, boolean isActive) {
    }
}
