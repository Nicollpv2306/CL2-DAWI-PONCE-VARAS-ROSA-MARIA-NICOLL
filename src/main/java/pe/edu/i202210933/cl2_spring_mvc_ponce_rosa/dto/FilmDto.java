package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto;

public record FilmDto(Integer filmId,
                      String title,
                      String language,
                      Integer rentalDuration,
                      Double rentalRate) {

}
