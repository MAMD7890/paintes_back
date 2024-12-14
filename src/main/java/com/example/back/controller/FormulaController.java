package com.example.back.controller;

import com.example.back.Dtos.FormulaWithArrayDTO;
import com.example.back.entity.Formula;
import com.example.back.Dtos.FormulaDTO;
import com.example.back.services.FormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formulas")
public class FormulaController {

    @Autowired
    private FormulaService formulaService;

    // Crear una nueva fórmula
    @PostMapping("/create")
    public ResponseEntity<Formula> createFormula(@RequestBody FormulaDTO formulaDTO) {
        // Crear la fórmula usando el servicio
        Formula formula = formulaService.createFormulaWithProductAndComponent(
                formulaDTO.getProductId(),
                formulaDTO.getComponentId(),
                formulaDTO.getUnitOfMeasurement(),
                formulaDTO.getAmount()
        );
        return new ResponseEntity<>(formula, HttpStatus.CREATED);
    }
    @PostMapping("/create-with-ingredients")
    public ResponseEntity<Formula> createFormulaWithIngredients(@RequestBody FormulaWithArrayDTO formulaWithArrayDTO) {
        Formula formula = formulaService.createFormulaWithIngredients(formulaWithArrayDTO);
        return new ResponseEntity<>(formula, HttpStatus.CREATED);
    }


    // Obtener todas las fórmulas
    @GetMapping("/")
    public ResponseEntity<List<Formula>> getAllFormulas() {
        List<Formula> formulas = formulaService.getAllFormulas();
        return new ResponseEntity<>(formulas, HttpStatus.OK);
    }

    // Obtener una fórmula por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Formula> getFormulaById(@PathVariable Integer id) {
        Optional<Formula> formula = formulaService.getFormulaById(id);
        return formula.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una fórmula
    @PutMapping("/{id}")
    public ResponseEntity<Formula> updateFormula(@PathVariable Integer id, @RequestBody FormulaDTO formulaDTO) {
        Optional<Formula> existingFormula = formulaService.getFormulaById(id);

        if (existingFormula.isPresent()) {
            Formula formula = existingFormula.get();
            formula.setProduct(formulaService.getProductById(formulaDTO.getProductId()));
            formula.setComponent(formulaService.getComponentById(formulaDTO.getComponentId()));
            formula.setUnitOfMeasurement(formulaDTO.getUnitOfMeasurement());
            formula.setAmount(formulaDTO.getAmount());
            formulaService.saveFormula(formula);
            return new ResponseEntity<>(formula, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una fórmula por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormula(@PathVariable Integer id) {
        Optional<Formula> formula = formulaService.getFormulaById(id);

        if (formula.isPresent()) {
            formulaService.deleteFormula(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteFormulasByProduct(@PathVariable Integer productId) {
        formulaService.deleteByProduct_Idproduct(productId);
        return ResponseEntity.noContent().build();
    }


    // Obtener fórmulas por el ID del producto
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Formula>> getFormulasByProduct(@PathVariable Integer productId) {
        List<Formula> formulas = formulaService.getFormulasByProduct(productId);
        return new ResponseEntity<>(formulas, HttpStatus.OK);
    }

    // Obtener fórmulas por el ID del componente
    @GetMapping("/component/{componentId}")
    public ResponseEntity<List<Formula>> getFormulasByComponent(@PathVariable Integer componentId) {
        List<Formula> formulas = formulaService.getFormulasByComponent(componentId);
        return new ResponseEntity<>(formulas, HttpStatus.OK);
    }


    @GetMapping("/all-transformed")
    public ResponseEntity<List<FormulaWithArrayDTO>> getAllFormulasTransformed() {
        List<FormulaWithArrayDTO> formulas = formulaService.getAllFormulasTransformed();
        return new ResponseEntity<>(formulas, HttpStatus.OK);
    }
}
