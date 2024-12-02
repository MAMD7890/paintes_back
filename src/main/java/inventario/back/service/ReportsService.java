package inventario.back.service;

import inventario.back.Entity.Reports;
import inventario.back.Repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {

    @Autowired
    private ReportsRepository reportsRepository;

    public List<Reports> findAll() {
        return reportsRepository.findAll();
    }

    public Reports findById(Integer id) {
        return reportsRepository.findById(id).orElse(null);
    }

    public Reports save(Reports reports) {
        return reportsRepository.save(reports);
    }

    public void deleteById(Integer id) {
        reportsRepository.deleteById(id);
    }
}
