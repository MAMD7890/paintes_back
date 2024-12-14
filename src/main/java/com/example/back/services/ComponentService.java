package com.example.back.services;



import com.example.back.entity.Component;
import com.example.back.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    // Obtener todos los componentes
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    // Obtener un componente por su ID
    public Optional<Component> getComponentById(Integer id) {
        return componentRepository.findById(id);
    }

    // Crear un nuevo componente
    public Component createComponent(Component component) {
        component.setCreatedAt(new Date());
        return componentRepository.save(component);
    }

    // Actualizar un componente existente
    public Component updateComponent(Integer id, Component componentDetails) {
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Componente no encontrado con id: " + id));

        component.setName(componentDetails.getName());
        component.setQuantity(componentDetails.getQuantity());
        component.setCurrentStock(componentDetails.getCurrentStock());
        component.setMinimumStock(componentDetails.getMinimumStock());
        component.setUnitOfMeasurement(componentDetails.getUnitOfMeasurement());
        component.setCreatedAt(componentDetails.getCreatedAt());
        component.setUpdatedAt(new Date());

        return componentRepository.save(component);
    }

    // Eliminar un componente por su ID
    public void deleteComponent(Integer id) {
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Componente no encontrado con id: " + id));
        componentRepository.delete(component);
    }
}