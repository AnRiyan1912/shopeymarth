package com.enigma.shopeymarth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "address", length = 100, nullable = false)
    private String address;
    @Column(name = "mobile_phone", length = 30, unique = true, nullable = false)
    private String mobilePhone;
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;
    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

    public Customer(String id, String name, String address, String mobilePhone, String email) {
    }
}
