package ru.yourtrip.repo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.repositories.PersonRepository;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final PersonRepository personRepository;

    public AuthController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public ModelAndView authPage() {
        return new ModelAndView("auth");
    }

    @PostMapping("/login")
    public void login(String login, String password) {

    }

    @PostMapping("/register")
    public void register(String login, String nickname, String avatar, String hash, String mail, Date birthday, String role, Boolean hidden_nickname, Boolean hidden_mail, Boolean hidden_birthday) {

    }
}