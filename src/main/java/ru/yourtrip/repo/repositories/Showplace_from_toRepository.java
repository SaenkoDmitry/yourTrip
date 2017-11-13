package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Showplace_from_to;

@Repository
public interface Showplace_from_toRepository extends JpaRepository<Showplace_from_to, Long> {
}