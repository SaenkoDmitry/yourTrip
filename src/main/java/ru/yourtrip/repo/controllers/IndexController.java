package ru.yourtrip.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.Route;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.repositories.RouteRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String login = ((User)auth.getPrincipal()).getUsername();
            Person person = personRepository.findByLogin(login);
            modelAndView.addObject("person", person);
            List<Route> routes = new ArrayList<>();
            routes.addAll(routeRepository.findAllByPersonId(person));
            modelAndView.addObject("routes", routes);
        }
        return modelAndView;
    }
}