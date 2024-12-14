package com.example.back.controller;

import com.example.back.entity.PackagingType;
import com.example.back.services.PackagingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packaging-types")
public class PackagingTypeController {

    @Autowired
    private PackagingTypeService service;

    @GetMapping
    public List<PackagingType> getAllPackagingTypes() {
        return service.getAllPackagingTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackagingType> getPackagingTypeById(@PathVariable int id) {
        return service.getPackagingTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PackagingType createPackagingType(@RequestBody PackagingType packagingType) {
        return service.createPackagingType(packagingType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackagingType> updatePackagingType(@PathVariable int id, @RequestBody PackagingType updatedPackagingType) {
        try {
            PackagingType updated = service.updatePackagingType(id, updatedPackagingType);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackagingType(@PathVariable int id) {
        service.deletePackagingType(id);
        return ResponseEntity.noContent().build();
    }
}
