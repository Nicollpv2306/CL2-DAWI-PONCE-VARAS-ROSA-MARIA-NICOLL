package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmCreateDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmDetailDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.LanguageDto;

import java.util.List;

public interface MaintenanceService {
    @Cacheable(value = "films")
    List<FilmDto> findAllFilms();

    FilmDetailDto findFilmById(int id);

    @CacheEvict(value = "films", allEntries = true)
    Boolean updateFilm(FilmDetailDto filmDetailDto);

    @CacheEvict(value = "films", allEntries = true)
    Boolean createFilm(FilmCreateDto filmCreateDto);

    @Cacheable(value = "languages")
    List<LanguageDto> findAllLanguages();

    @CacheEvict(value = "films", allEntries = true)
    Boolean deleteFilm(int id);
}
