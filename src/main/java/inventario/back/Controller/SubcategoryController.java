package inventario.back.Controller;

import inventario.back.Entity.Subcategory;
import inventario.back.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping
    public List<Subcategory> getAllSubcategories() {
        return subcategoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable Integer id) {
        Optional<Subcategory> subcategory = subcategoryService.findById(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Subcategory createSubcategory(@RequestBody Subcategory subcategory) {
        return subcategoryService.save(subcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Integer id, @RequestBody Subcategory subcategory) {
        return subcategoryService.findById(id).map(existingSubcategory -> {
            subcategory.setIdsubcategory(existingSubcategory.getIdsubcategory());
            return ResponseEntity.ok(subcategoryService.save(subcategory));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Integer id) {
        if (subcategoryService.findById(id).isPresent()) {
            subcategoryService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
