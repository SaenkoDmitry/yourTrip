package ru.yourtrip.repo.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.User;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.utils.JwtUtil;
import ru.yourtrip.repo.utils.TokenGenerator;

import javax.servlet.http.HttpServletResponse;
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

//    @GetMapping("/")
//    public ModelAndView authPage() {
//        return new ModelAndView("auth");
//    }

//    @PostMapping("/login")
//    public String login(@RequestBody User user) {
//        if(SecurityContextHolder.getContext().getAuthentication() != null) {
//            return "redirect:/";
//        }
//        return "/";
//    }

    @PostMapping("/sign-test")
    public String test() {
        return "Success";
    }

    @PostMapping("/sign-up")
    public void register(@RequestBody Person person) {
        person.setHash(bCryptPasswordEncoder.encode(person.getHash()));
        person.setRole("normal");
        try {
            personRepository.save(person);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}