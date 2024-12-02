package inventario.back.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcategory;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 100)
    private String description;
}
