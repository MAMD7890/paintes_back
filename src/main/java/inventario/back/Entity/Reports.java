package inventario.back.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reports")
@Data
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreports;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
