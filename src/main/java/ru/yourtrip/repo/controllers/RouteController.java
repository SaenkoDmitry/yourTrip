package ru.yourtrip.repo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.repositories.RouteRepository;
import ru.yourtrip.repo.models.*;

import java.util.*;

@RestController
@RequestMapping("/routes")
public class RouteController {
    final PersonRepository personRepository;
    final RouteRepository routeRepository;


    public RouteController(PersonRepository personRepository, RouteRepository routeRepository) {
        this.personRepository = personRepository;
        this.routeRepository = routeRepository;
    }

    @GetMapping("/")
    public ModelAndView getAllRoutes() {
        List<Route> model = routeRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("prods", model);
        return modelAndView;
    }

    @GetMapping("/route1")
    public ModelAndView getRoute() {
        List<Route> model = new ArrayList<>();
        Person person = new Person("testik", "testikov", "ssa", "dfdsfsd", "email.com", new Date(System.currentTimeMillis()), Role.normal.toString(), true, true, true);
        personRepository.save(person);
//        Route route = new Route("name1", "ds", true, true, Category.active.toString(), 5.0, 4.0, 4.5, person);
//        model.add(route);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("prods", model);
        return modelAndView;
    }
}