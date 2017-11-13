package ru.yourtrip.repo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourtrip.repo.repositories.PersonRepository;
import ru.yourtrip.repo.repositories.SubscriptionRepository;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    final PersonRepository personRepository;
    final SubscriptionRepository subscriptionRepository;

    public SubscriptionController(PersonRepository personRepository, SubscriptionRepository subscriptionRepository) {
        this.personRepository = personRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping("/subscribe")
    public void subscribe(Long idFromPerson, Long idToPerson) {

    }

    @PostMapping("/unsubscribe")
    public void unsubscribe(Long idFromPerson, Long idToPerson) {

    }
}