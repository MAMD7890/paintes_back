package com.example.back.controller;

import com.example.back.entity.Packing;
import com.example.back.services.PackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packings")
public class PackingController {

    @Autowired
    private PackingService service;

    @GetMapping
    public List<Packing> getAllPackings() {
        return service.getAllPackings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Packing> getPackingById(@PathVariable int id) {
        return service.getPackingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public List<Packing> createPacking(@RequestBody Iterable<Packing> packing) {
        return service.createPacking(packing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Packing> updatePacking(@PathVariable int id, @RequestBody Packing updatedPacking) {
        try {
            Packing updated = service.updatePacking(id, updatedPacking);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacking(@PathVariable int id) {
        service.deletePacking(id);
        return ResponseEntity.noContent().build();
    }
}