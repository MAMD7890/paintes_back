package com.example.back.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role")
public class Role {
    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrole;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
