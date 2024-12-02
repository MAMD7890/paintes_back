package inventario.back.Repository;

import inventario.back.Entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportsRepository extends JpaRepository<Reports, Integer> {
}
