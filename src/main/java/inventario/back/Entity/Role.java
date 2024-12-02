package inventario.back.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrole;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
