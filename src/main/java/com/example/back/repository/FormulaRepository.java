package com.example.back.repository;


import com.example.back.entity.Formula;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaRepository extends JpaRepository<Formula, Integer> {
    List<Formula> findByProduct_Idproduct(Integer productId);
    List<Formula> findByComponent_Idcomponent(Integer componentId);

    @Transactional
    void deleteByProduct_Idproduct(Integer productId);

}
