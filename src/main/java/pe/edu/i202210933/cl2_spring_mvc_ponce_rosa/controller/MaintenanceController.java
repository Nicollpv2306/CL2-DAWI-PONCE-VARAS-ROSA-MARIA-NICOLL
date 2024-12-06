package pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmCreateDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmDetailDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.FilmDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.dto.LanguageDto;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.repository.LanguageRepository;
import pe.edu.i202210933.cl2_spring_mvc_ponce_rosa.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;
    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping("/start")
    private String start(Model model) {

        List<FilmDto> films = maintenanceService.findAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail( @PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance_detail";
    }


    @GetMapping("/edit/{id}")
    public String edit( @PathVariable Integer id, Model model) {
        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance_edit";
    }

    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute FilmDetailDto filmDetailDto, Model model) {
        maintenanceService.updateFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<LanguageDto> languages = maintenanceService.findAllLanguages();
        model.addAttribute("languages", languages);
        return "maintenance_create";
    }

    @PostMapping("/create")
    public String createFilm(@ModelAttribute FilmCreateDto filmCreateDto) {
        boolean success = maintenanceService.createFilm(filmCreateDto);
        if (success) {
            return "redirect:/maintenance/start";
        }
        return "redirect:/maintenance/create";
    }

    @PostMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Integer id, Model model) {
        System.out.println("Eliminando " + id);
        try {
            maintenanceService.deleteFilm(id);
            return "redirect:/maintenance/start";
        } catch (Exception e) {
            return "redirect:/maintenance/start";
        }
    }
}
