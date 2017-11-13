package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}