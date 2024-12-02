package inventario.back.service;

import inventario.back.Entity.Component;
import inventario.back.Repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {
    @Autowired
    private ComponentRepository componentRepository;

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Component getComponentById(int id) {
        return componentRepository.findById(id).orElse(null);
    }

    public Component createComponent(Component component) {
        return componentRepository.save(component);
    }

    public Component updateComponent(int id, Component componentDetails) {
        Component component = componentRepository.findById(id).orElse(null);
        if (component != null) {
            component.setName(componentDetails.getName());
            component.setPrice(componentDetails.getPrice());
            component.setStock(componentDetails.getStock());
            component.setCreatedAt(componentDetails.getCreatedAt());
            component.setUpdatedAt(componentDetails.getUpdatedAt());
            return componentRepository.save(component);
        }
        return null;
    }

    public void deleteComponent(int id) {
        componentRepository.deleteById(id);
    }
}