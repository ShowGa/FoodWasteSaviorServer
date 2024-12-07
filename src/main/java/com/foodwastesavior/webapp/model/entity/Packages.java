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
@Table(name = "Packages")
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Integer packageId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "cover_image_url", length = 255)
    private String coverImageUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "allergens_desc", columnDefinition = "TEXT")
    private String allergensDesc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Category category;

    @Column(name = "origin_price", nullable = false)
    private Integer originPrice;

    @Column(name = "discount_price", nullable = false)
    private Integer discountPrice;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true; // Default true

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ========== Define Relation Table ========== //
    // Stores
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Stores store;

    // Package_Sales_Rules
    // Orders
    // Favorites

    // ========== Create AddressType enum Class ========== //
    public enum Category {
        MEALS, BAKERY, GROCERY, OTHERS
    }
}

