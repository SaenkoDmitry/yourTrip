package ru.yourtrip.repo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.yourtrip.repo.models.Person;
import ru.yourtrip.repo.models.Route;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByPersonId(Person personId);

    public Route findByRoute_name(String route_name);

//    @Modifying
//    @Query("UPDATE route c SET c.route_name = :route_name, " +
//            "c.commentary = :commentary," +
//            "c.complete = :complete," +
//            "c.hidden = :hidden," +
//            "c.category = :category," +
//            "c.mark = :mark," +
//            "c.lowerPrice = :lowerPrice," +
//            "c.upperPrice = :upperPrice," +
//            "c.personId = :personId" +
//            " WHERE c.id = :id")
//    void updateRoute(@Param("id") Long id,
//                    @Param("route_name") String route_name,
//                    @Param("commentary") String commentary,
//                    @Param("complete") Boolean complete,
//                    @Param("hidden") Boolean hidden,
//                    @Param("category") String category,
//                    @Param("mark") Double mark,
//                    @Param("lowerPrice") Integer lowerPrice,
//                    @Param("upperPrice") Integer upperPrice,
//                    @Param("personId") Person personId);
}