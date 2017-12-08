package ru.yourtrip.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.repositories.PersonRepository;

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
        ModelAndView modelAndView = new ModelAndView("login");

        person.setHash(bCryptPasswordEncoder.encode(person.getHash()));
        person.setRole("normal");
        person.setAvatar("None");
        person.setHidden_nickname(true);
        person.setHidden_mail(true);
        person.setHidden_birthday(true);
        try {
            personRepository.save(person);
            modelAndView.setStatus(HttpStatus.OK);
        } catch(RuntimeException ex) {
            System.out.println(ex.getMessage());
            modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return modelAndView;
    }

    @PostMapping(value="/edit-info")
    public ModelAndView editInfo(Person updatedPerson) {
        ModelAndView modelAndView = new ModelAndView("index");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = ((User)auth.getPrincipal()).getUsername();

        Person oldPerson = personRepository.findByLogin(updatedPerson.getLogin());
        if (oldPerson != null && login.equals(updatedPerson.getLogin())) {
            updatedPerson.setRole(oldPerson.getRole());
            updatedPerson.setHash(bCryptPasswordEncoder.encode(updatedPerson.getHash()));
            updatedPerson.setId(oldPerson.getId());
            try {
                personRepository.save(updatedPerson);
                modelAndView.setStatus(HttpStatus.OK);
            } catch(RuntimeException ex) {
                System.out.println(ex.getMessage());
                modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        }
        return modelAndView;
    }
}