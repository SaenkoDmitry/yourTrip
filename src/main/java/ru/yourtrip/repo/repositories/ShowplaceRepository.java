package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Showplace;

@Repository
public interface ShowplaceRepository extends JpaRepository<Showplace, Long> {
    Showplace findByShowplace_name(String showplace_name);
}