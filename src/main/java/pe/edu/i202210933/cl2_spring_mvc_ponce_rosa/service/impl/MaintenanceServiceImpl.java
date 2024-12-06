package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmCreateDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmDetailDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.LanguageDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.entity.Film;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.entity.Language;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.repository.FilmRepository;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.repository.LanguageRepository;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Override
    public List<LanguageDto> findAllLanguages() {
        List<LanguageDto> languageDtos = new ArrayList<>();
        List<Language> languages = languageRepository.findAll();
        for (Language language : languages) {
            languageDtos.add(new LanguageDto(language.getLanguageId(), language.getName()));
        }
        return languageDtos;
    }

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<FilmDto> findAllFilms() {

        List<FilmDto> films = new ArrayList<>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;

    }

    @Override
    public FilmDetailDto findFilmById(int id) {

        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> new FilmDetailDto(film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getLanguageId(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate())
        ).orElse(null);

    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
                film -> {
                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public Boolean createFilm(FilmCreateDto filmCreateDto) {
        Optional<Language> languageOptional = languageRepository.findById(filmCreateDto.languageId());
        if (languageOptional.isPresent()) {
            Language language = languageOptional.get();
            Film film = new Film();
            film.setTitle(filmCreateDto.title());
            film.setDescription(filmCreateDto.description());
            film.setReleaseYear(filmCreateDto.releaseYear());
            film.setLanguage(language);
            film.setRentalDuration(filmCreateDto.rentalDuration());
            film.setRentalRate(filmCreateDto.rentalRate());
            film.setLength(filmCreateDto.length());
            film.setReplacementCost(filmCreateDto.replacementCost());
            film.setRating(filmCreateDto.rating());
            film.setSpecialFeatures(filmCreateDto.specialFeatures());
            film.setLastUpdate(new Date());
            filmRepository.save(film);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteFilm(int id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()) {
            filmRepository.delete(film.get());
            return true;
        }
        return false;
    }
}