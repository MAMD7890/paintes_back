package com.example.back.services;

import com.example.back.entity.Component;
import com.example.back.entity.Formula;
import com.example.back.entity.Packing;
import com.example.back.repository.ComponentRepository;
import com.example.back.repository.FormulaRepository;
import com.example.back.repository.PackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackingService {

    @Autowired
    private PackingRepository repository;
    @Autowired
    private FormulaRepository formulaRepository;
    @Autowired
    private ComponentRepository componentRepository;
    public List<Packing> getAllPackings() {
        return repository.findAll();
    }

    public Optional<Packing> getPackingById(int id) {
        return repository.findById(id);
    }

    public List<Packing> createPacking(Iterable<Packing> packing) {
        for (Packing pack : packing) {
            if(pack.getPackagingTypeId().getIdpackagingType() ==1){
                List<Formula> formulas = formulaRepository.findByProduct_Idproduct(pack.getProduct().getIdproduct());
                for (Formula formula : formulas) {
                    double galon = 3.78541;
                    double amount = formula.getAmount() * galon;
                    Component component = componentRepository.getReferenceById(formula.getComponent().getIdcomponent());
                    component.setCurrentStock(String.valueOf(Double.valueOf(component.getCurrentStock()) - amount));
                    componentRepository.save(component);
                }
            }
        }
        return repository.saveAll(packing);
    }

    public Packing updatePacking(int id, Packing updatedPacking) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedPacking.getName());
            existing.setQuantity(updatedPacking.getQuantity());
            existing.setPackagingTypeId(updatedPacking.getPackagingTypeId());
            existing.setUserId(updatedPacking.getUserId());
            existing.setProduct(updatedPacking.getProduct());


            return repository.save(existing);

        }).orElseThrow(() -> new RuntimeException("Packing not found with id " + id));
    }

    public void deletePacking(int id) {
        repository.deleteById(id);
    }
}
