package ru.yourtrip.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.Route;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.repositories.RouteRepository;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RouteRepository routeRepository;

    @PostMapping("/changeRoute")
    @ResponseBody
    public ResponseEntity changeRoute(@ModelAttribute Route route/*, HttpServletResponse response*/) {
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String login = ((User)auth.getPrincipal()).getUsername();
            Person person = personRepository.findByLogin(login);
            try {
                Route route1 = routeRepository.findOne(route.getId());
                if (person.getId().equals(route1.getPersonId().getId())) {
//                    routeRepository.updateRoute(route.getId(),
//                            route.getRoute_name(),
//                            route.getCommentary(),
//                            route.getComplete(),
//                            route.getHidden(),
//                            route.getCategory(),
//                            route.getMark(),
//                            route.getLowerPrice(),
//                            route.getUpperPrice(),
//                            route.getPersonId());
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
//                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
                }
            } catch(Exception ex) {
                System.out.println(ex);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value="/creatingRoute")
    public ModelAndView publishRoutePage() {
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
        String login = ((User)auth.getPrincipal()).getUsername();
        route.setPersonId(personRepository.findByLogin(login));
        try {
            routeRepository.save(route);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/routes/{id}")
    public ModelAndView showRoute(@PathVariable(value="id") Long id) {
        Route route = routeRepository.findOne(id);
        ModelAndView modelAndView = new ModelAndView("route");
        modelAndView.addObject("route", route);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }
}