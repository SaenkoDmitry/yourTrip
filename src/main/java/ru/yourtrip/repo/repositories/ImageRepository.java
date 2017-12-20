package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Image;
import ru.yourtrip.repo.models.Route;

import java.util.ArrayList;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    ArrayList<Image> findByRouteId(Route routeId);
}