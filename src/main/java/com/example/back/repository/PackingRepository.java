package com.example.back.repository;

import com.example.back.entity.Packing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingRepository extends JpaRepository<Packing, Integer> {
}