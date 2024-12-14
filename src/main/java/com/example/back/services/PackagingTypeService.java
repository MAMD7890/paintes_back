package com.example.back.services;

import com.example.back.entity.PackagingType;
import com.example.back.repository.PackagingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackagingTypeService {

    @Autowired
    private PackagingTypeRepository repository;

    public List<PackagingType> getAllPackagingTypes() {
        return repository.findAll();
    }

    public Optional<PackagingType> getPackagingTypeById(int id) {
        return repository.findById(id);
    }

    public PackagingType createPackagingType(PackagingType packagingType) {
        return repository.save(packagingType);
    }

    public PackagingType updatePackagingType(int id, PackagingType updatedPackagingType) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedPackagingType.getName());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Tipo de empaque no encontrado con id" + id));
    }

    public void deletePackagingType(int id) {
        repository.deleteById(id);
    }
}