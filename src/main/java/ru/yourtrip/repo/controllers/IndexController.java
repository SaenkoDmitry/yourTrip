package ru.yourtrip.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.repositories.PersonRepository;

@Controller
public class IndexController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String id = ((User)auth.getPrincipal()).getUsername();
            Person person = personRepository.getOne(Long.parseLong(id));
            modelAndView.addObject("person", person);
        }
        return modelAndView;
    }
}