package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class FilmActorId implements Serializable {

    private Integer filmId;
    private Integer actorId;
}