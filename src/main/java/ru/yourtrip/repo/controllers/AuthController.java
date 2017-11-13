package ru.yourtrip.repo.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.User;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.utils.TokenGenerator;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final PersonRepository personRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/")
    public ModelAndView authPage() {
        return new ModelAndView("auth");
    }

    @PostMapping("/sign-in")
    public String login(@RequestBody User user) {
        Person person = personRepository.findByLogin(user.getLogin());
        if (person != null && bCryptPasswordEncoder.matches(user.getPassword(), person.getHash())) {
            return TokenGenerator.getToken(person);
        }
        else {
            return "Failed";
        }
    }

    @PostMapping("/sign-test")
    public String test() {
        return "Success";
    }

    @PostMapping("/sign-up")
    public void register(@RequestBody Person person) {
        person.setHash(bCryptPasswordEncoder.encode(person.getHash()));
        person.setRole("normal");
        personRepository.save(person);
    }
}