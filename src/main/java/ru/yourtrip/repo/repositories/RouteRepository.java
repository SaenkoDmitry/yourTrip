package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}