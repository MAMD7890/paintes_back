package inventario.back.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idworker;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    private Double salary;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

}