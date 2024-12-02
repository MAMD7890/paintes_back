package inventario.back.service;

import inventario.back.Entity.Subcategory;
import inventario.back.Repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> findAll() {
        return subcategoryRepository.findAll();
    }

    public Optional<Subcategory> findById(Integer id) {
        return subcategoryRepository.findById(id);
    }

    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public void deleteById(Integer id) {
        subcategoryRepository.deleteById(id);
    }
}
