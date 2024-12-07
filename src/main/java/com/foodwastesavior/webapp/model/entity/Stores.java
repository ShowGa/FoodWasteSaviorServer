package com.foodwastesavior.webapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Stores")
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "cover_image_url", length = 255)
    private String coverImageUrl;

    @Column(name = "logo_image_url", length = 255)
    private String logoImageUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String about;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ========== Define Relation Table ========== //
    // Address
    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    private Address address;

    // Packages (additional)
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Packages> packages;

    // Orders
    @OneToMany(mappedBy = "store")
    private List<Orders> orders;

    // Reviews
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Reviews> reviews;
}

