package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}