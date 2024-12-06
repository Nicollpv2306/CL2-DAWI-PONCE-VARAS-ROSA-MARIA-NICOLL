package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto;

public record FilmCreateDto(String title,
                            String description,
                            Integer releaseYear,
                            Integer languageId,
                            Integer rentalDuration,
                            Double rentalRate,
                            Integer length,
                            Double replacementCost,
                            String rating,
                            String specialFeatures) {
}