package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Route_showplace_list;

@Repository
public interface Route_showplace_listRepository extends JpaRepository<Route_showplace_list, Long> {
}