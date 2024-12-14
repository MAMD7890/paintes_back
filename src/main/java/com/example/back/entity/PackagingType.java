package com.example.back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "packaging_type")
public class PackagingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpackagingType;

    private String name;

    // Getters and Setters
    public int getIdpackagingType() {
        return idpackagingType;
    }

    public void setIdpackagingType(int idpackagingType) {
        this.idpackagingType = idpackagingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
