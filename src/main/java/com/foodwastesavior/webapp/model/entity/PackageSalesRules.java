package com.foodwastesavior.webapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Package_Sales_Rules")
public class PackageSalesRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rules_id")
    private Integer rulesId;

    @Column(nullable = false)
    private int weekday; // Check 0 - 6 inside the services

    @Column(nullable = false)
    private int quantity; // check >= 0 inside the service

    @Column(name = "pickup_start_time")
    private LocalTime pickupStartTime;

    @Column(name = "pickup_end_time")
    private LocalTime pickupEndTime;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true; // Default true

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ========== Define Relation Table ========== //
    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Packages pack; //  Packages
}

