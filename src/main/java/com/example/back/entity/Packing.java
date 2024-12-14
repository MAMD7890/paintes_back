package com.example.back.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "packing")
public class Packing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpacking;

    private String name;
    private int quantity;

    @ManyToOne   @JoinColumn(name = "packaging_type_idpackaging_type", nullable = true)
    private PackagingType packagingTypeId;

    @ManyToOne(cascade = CascadeType.PERSIST)    @JoinColumn(name = "user_iduser", nullable = true)
    private User userId;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @ManyToOne    @JoinColumn(name = "product", nullable = true)
    private Product product;

    public int getIdpacking() {
        return idpacking;
    }

    public void setIdpacking(int idpacking) {
        this.idpacking = idpacking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PackagingType getPackagingTypeId() {
        return packagingTypeId;
    }

    public void setPackagingTypeId(PackagingType packagingTypeId) {
        this.packagingTypeId = packagingTypeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
