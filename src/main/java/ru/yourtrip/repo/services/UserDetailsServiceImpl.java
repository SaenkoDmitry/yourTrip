package ru.yourtrip.repo.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.repositories.PersonRepository;

import java.util.Date;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByLogin(username);
        if (person == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(person.getLogin(), person.getHash(), emptyList());
    }
}