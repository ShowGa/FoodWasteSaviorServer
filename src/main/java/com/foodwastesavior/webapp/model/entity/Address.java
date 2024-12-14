package com.foodwastesavior.webapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    // Nullable for default
    @Column(name = "address_detail", length = 255)
    private String addressDetail;

    @Column(length = 100)
    private String country;

    @Column(length = 100)
    private String state;

    @Column(length = 100)
    private String city;

    // Nullable for default
    @Column(name = "postal_code", length = 20)
    private Integer postalCode;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AddressType type; // Distinguish 'STORE' and 'USER' with type

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ========== Define Relation Table ========== //
    // User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Store
    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    // ========== Create AddressType enum Class ========== //
    public enum AddressType {
        STORE, USER
    }
}

