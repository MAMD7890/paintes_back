package com.example.back.controller;

import com.example.back.entity.Component;
import com.example.back.services.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/components")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    // Obtener todos los componentes
    @GetMapping
    public List<Component> getAllComponents() {
        return componentService.getAllComponents();
    }

    // Obtener un componente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Component> getComponentById(@PathVariable Integer id) {
        Optional<Component> component = componentService.getComponentById(id);
        return component.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo componente
    @PostMapping
    public ResponseEntity<Component> createComponent(@RequestBody Component component) {
        Component createdComponent = componentService.createComponent(component);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComponent);
    }

    // Actualizar un componente existente
    @PutMapping("/{id}")
    public ResponseEntity<Component> updateComponent(@PathVariable Integer id, @RequestBody Component componentDetails) {
        try {
            Component updatedComponent = componentService.updateComponent(id, componentDetails);
            return ResponseEntity.ok(updatedComponent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar un componente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable Integer id) {
        try {
            componentService.deleteComponent(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}