package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer originalLanguageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @OneToMany(mappedBy = "film", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<FilmActor> filmActors;

    @OneToMany(mappedBy = "film", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<FilmCategory> filmCategories;

    @OneToMany(mappedBy = "film", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Inventory> inventories;

}