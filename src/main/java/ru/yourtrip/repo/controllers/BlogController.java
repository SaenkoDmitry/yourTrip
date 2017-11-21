package ru.yourtrip.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.Route;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.repositories.RouteRepository;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RouteRepository routeRepository;

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

    @GetMapping(value="/creatingRoute")
    public ModelAndView publishRoutePage(/*@RequestParam("username") String username*/) {
//        Person person = personRepository.findByLogin(username);
        ModelAndView modelAndView = new ModelAndView("createroute");
        Route route = new Route();
        modelAndView.addObject("route", route);
        return modelAndView;
    }

    @PostMapping(value="/creatingRoute", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView publishRoute(@ModelAttribute Route route) {
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        String id = ((User)auth.getPrincipal()).getUsername();
        route.setPerson_id(personRepository.getOne(Long.parseLong(id)));
        try {
            routeRepository.save(route);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new ModelAndView("redirect:/");

    }
}