package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    public Subscription getByPersonSAndPersonS2(Person personS, Person personS2);
}