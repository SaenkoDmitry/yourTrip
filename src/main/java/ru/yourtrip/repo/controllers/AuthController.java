package ru.yourtrip.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.Route;
import ru.yourtrip.repo.models.User;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.utils.JwtUtil;
import ru.yourtrip.repo.utils.TokenGenerator;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value="/sign-up", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView register(Person person) {
        person.setHash(bCryptPasswordEncoder.encode(person.getHash()));
        person.setRole("normal");
        person.setAvatar("None");
        person.setHidden_nickname(true);
        person.setHidden_mail(true);
        person.setHidden_birthday(true);
        try {
            personRepository.save(person);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ModelAndView("login");
    }
}