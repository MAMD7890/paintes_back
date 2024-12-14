package com.example.back.repository;

import com.example.back.entity.PackagingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagingTypeRepository extends JpaRepository<PackagingType, Integer> {
}