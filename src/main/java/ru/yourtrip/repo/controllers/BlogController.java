package ru.yourtrip.repo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.repositories.RouteRepository;

@RestController
@RequestMapping("/blog")
public class BlogController {

    final PersonRepository personRepository;
    final RouteRepository routeRepository;

    public BlogController(PersonRepository personRepository, RouteRepository routeRepository) {
        this.personRepository = personRepository;
        this.routeRepository = routeRepository;
    }

    @GetMapping("/")
    public ModelAndView blogPage() {
        return new ModelAndView("blog");
    }

    @GetMapping("/newRoute")
    public ModelAndView newRoute() {
        return new ModelAndView("blog");
    }

    @PostMapping("/changeRoute")
    public void changeRoute(String route_name, String commentary, Boolean complete, Boolean hidden, String category, Double mark_complexity, Double mark_culture, Double mark_entertainment, Long person) {

    }

    @PostMapping("/publishRoute")
    public void publishRoute(String route_name, String commentary, Boolean complete, Boolean hidden, String category, Double mark_complexity, Double mark_culture, Double mark_entertainment, Long person) {

    }
}