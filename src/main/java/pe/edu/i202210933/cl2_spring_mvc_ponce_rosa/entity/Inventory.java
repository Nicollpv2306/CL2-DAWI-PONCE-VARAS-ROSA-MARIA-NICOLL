package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @Column(nullable = false)
    private String store_id;
}
