package inventario.back.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "component")
@Data
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcomponent;
    @Column(name ="name")
    private String name;
    @Column(name ="price")
    private double price;
    @Column(name ="stock")
    private int stock;
    @Column(name ="created_at")
    private java.util.Date createdAt;
    @Column(name = "updated_at")
    private java.util.Date updatedAt;
}