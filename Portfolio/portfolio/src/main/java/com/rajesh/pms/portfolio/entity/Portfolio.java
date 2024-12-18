package com.rajesh.pms.portfolio.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "portfolios")
@Getter
@Setter
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID portfolioId;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
    
    @Column(name = "user_id")
    private UUID userId; // Store userId directly instead of a User object
   

    @Column(name= "name", nullable = false)
    private String portfolioName;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Getters and Setters
}
