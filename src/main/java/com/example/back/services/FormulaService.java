package com.example.back.services;

import com.example.back.Dtos.FormulaWithArrayDTO;
import com.example.back.Dtos.IngredientDTO;
import com.example.back.entity.Formula;
import com.example.back.entity.Product;
import com.example.back.entity.Component;
import com.example.back.repository.FormulaRepository;
import com.example.back.repository.ProductRepository;
import com.example.back.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormulaService {

    @Autowired
    private FormulaRepository formulaRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentRepository componentRepository;

    public List<Formula> getAllFormulas() {
        return formulaRepository.findAll();
    }

    public Optional<Formula> getFormulaById(Integer id) {
        return formulaRepository.findById(id);
    }

    public Formula saveFormula(Formula formula) {
        return formulaRepository.save(formula);
    }

    public void deleteFormula(Integer id) {
        formulaRepository.deleteById(id);
    }

    public Formula createFormulaWithIngredients(FormulaWithArrayDTO formulaWithArrayDTO) {
        List<Formula> formulaBd = formulaRepository.findByProduct_Idproduct(formulaWithArrayDTO.getProducto());
        if (!formulaBd.isEmpty()) {
            formulaRepository.deleteByProduct_Idproduct(formulaWithArrayDTO.getProducto());

        }
        Product product = productRepository.findById(formulaWithArrayDTO.getProducto())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + formulaWithArrayDTO.getProducto()));

        List<Formula> ingredientFormulas = new ArrayList<>();

        for (IngredientDTO ingredientDTO : formulaWithArrayDTO.getIngredientes()) {
            Component component = componentRepository.findById(ingredientDTO.getNombre())
                    .orElseThrow(() -> new RuntimeException("Component not found with ID: " + ingredientDTO.getNombre()));

            Formula formula = new Formula();
            formula.setProduct(product);
            formula.setComponent(component);
            formula.setUnitOfMeasurement(component.getUnitOfMeasurement());
            formula.setAmount(ingredientDTO.getCantidad());

            formulaRepository.save(formula);
            ingredientFormulas.add(formula);
        }

        return ingredientFormulas.isEmpty() ? null : ingredientFormulas.get(0);
    }

    public Formula updateFormulaWithIngredients(FormulaWithArrayDTO formulaWithArrayDTO) {
        formulaRepository.deleteByProduct_Idproduct(formulaWithArrayDTO.getProducto());
        Product product = productRepository.findById(formulaWithArrayDTO.getProducto())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + formulaWithArrayDTO.getProducto()));

        List<Formula> ingredientFormulas = new ArrayList<>();

        for (IngredientDTO ingredientDTO : formulaWithArrayDTO.getIngredientes()) {
            Component component = componentRepository.findById(ingredientDTO.getNombre())
                    .orElseThrow(() -> new RuntimeException("Component not found with ID: " + ingredientDTO.getNombre()));

            Formula formula = new Formula();
            formula.setProduct(product);
            formula.setComponent(component);
            formula.setUnitOfMeasurement(component.getUnitOfMeasurement());
            formula.setAmount(ingredientDTO.getCantidad());

            formulaRepository.save(formula);
            ingredientFormulas.add(formula);
        }

        return ingredientFormulas.isEmpty() ? null : ingredientFormulas.get(0);
    }

    public List<Formula> getFormulasByProduct(Integer productId) {
        return formulaRepository.findByProduct_Idproduct(productId);
    }

    public List<Formula> getFormulasByComponent(Integer componentId) {
        return formulaRepository.findByComponent_Idcomponent(componentId);
    }

    public Formula createFormulaWithProductAndComponent(Integer productId, Integer componentId, String unitOfMeasurement, Double amount) {
        // Buscar el producto por su ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Buscar el componente por su ID
        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        // Crear la fórmula y asignar los valores
        Formula formula = new Formula();
        formula.setProduct(product);
        formula.setComponent(component);
        formula.setUnitOfMeasurement(component.getUnitOfMeasurement());
        formula.setAmount(amount);

        // Guardar la fórmula
        return formulaRepository.save(formula);
    }
    // Métodos para obtener Product y Component por su ID
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Component getComponentById(Integer componentId) {
        return componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));
    }
    public List<FormulaWithArrayDTO> getAllFormulasTransformed() {
        // Obtener todas las fórmulas
        List<Formula> formulas = formulaRepository.findAll();

        // Agrupar fórmulas por producto
        Map<Integer, List<Formula>> groupedByProduct = formulas.stream()
                .collect(Collectors.groupingBy(formula -> formula.getProduct().getIdproduct()));

        // Transformar las fórmulas agrupadas en FormulaWithArrayDTO
        List<FormulaWithArrayDTO> result = groupedByProduct.entrySet().stream()
                .map(entry -> {
                    Integer productId = entry.getKey();
                    List<Formula> productFormulas = entry.getValue();

                    // Obtener el nombre del producto (asumiendo que todas las fórmulas del grupo tienen el mismo producto)
                    String productName = productFormulas.get(0).getProduct().getName();
                    String productUrlImagen=productFormulas.get(0).getProduct().getImageUrl();

                    // Crear los IngredientDTO
                    List<IngredientDTO> ingredientes = productFormulas.stream()
                            .map(formula -> {
                                IngredientDTO ingredientDTO = new IngredientDTO();
                                ingredientDTO.setNombre(formula.getComponent().getIdcomponent());// Usamos el ID del componente como nombre
                                ingredientDTO.setName(formula.getComponent().getName());
                                ingredientDTO.setCantidad(formula.getAmount());
                                ingredientDTO.setUnidad(formula.getComponent().getUnitOfMeasurement());
                                return ingredientDTO;
                            })
                            .collect(Collectors.toList());

                    // Crear el FormulaWithArrayDTO
                    FormulaWithArrayDTO formulaDTO = new FormulaWithArrayDTO();
                    formulaDTO.setProducto(productId);
                    formulaDTO.setNameProducto(productName); // Asignar el nombre del producto
                    formulaDTO.setIngredientes(ingredientes);
                    formulaDTO.setUrlImagen(productUrlImagen);

                    return formulaDTO;
                })
                .collect(Collectors.toList());

        return result;
    }

    @Autowired
    public FormulaService(FormulaRepository formulaRepository) {
        this.formulaRepository = formulaRepository;
    }

    public void deleteByProduct_Idproduct(Integer productId) {
        formulaRepository.deleteByProduct_Idproduct(productId);
    }


}

