package inventario.back.Controller;

import inventario.back.Entity.Component;
import inventario.back.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/components")
public class ComponentController {
    @Autowired
    private ComponentService componentService;

    @GetMapping
    public List<Component> getAllComponents() {
        return componentService.getAllComponents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Component> getComponentById(@PathVariable int id) {
        Component component = componentService.getComponentById(id);
        return ResponseEntity.ok(component);
    }

    @PostMapping
    public Component createComponent(@RequestBody Component component) {
        return componentService.createComponent(component);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Component> updateComponent(@PathVariable int id, @RequestBody Component componentDetails) {
        Component updatedComponent = componentService.updateComponent(id, componentDetails);
        return ResponseEntity.ok(updatedComponent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponent(@PathVariable int id) {
        componentService.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}