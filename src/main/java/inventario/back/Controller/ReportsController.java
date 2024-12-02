package inventario.back.Controller;

import inventario.back.Entity.Reports;
import inventario.back.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping
    public List<Reports> findAll() {
        return reportsService.findAll();
    }

    @GetMapping("/{id}")
    public Reports findById(@PathVariable Integer id) {
        return reportsService.findById(id);
    }

    @PostMapping
    public Reports save(@RequestBody Reports reports) {
        return reportsService.save(reports);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        reportsService.deleteById(id);
    }
}

