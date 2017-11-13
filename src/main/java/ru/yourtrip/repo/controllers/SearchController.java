package ru.yourtrip.repo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.repositories.RouteRepository;
import ru.yourtrip.repo.repositories.ShowplaceRepository;

@RestController
@RequestMapping("/search")
public class SearchController {

    final RouteRepository routeRepository;
    final ShowplaceRepository showplaceRepository;

    public SearchController(RouteRepository routeRepository, ShowplaceRepository showplaceRepository) {
        this.routeRepository = routeRepository;
        this.showplaceRepository = showplaceRepository;
    }

    @GetMapping("/")
    public ModelAndView searchPage(String []city, String []showplace, Integer distance, Integer spentTime, Integer minNumOfShowplace, Integer maxNumOfShowplace, String category, Boolean ascendingDate, Boolean ascendingRaiting) {
        return new ModelAndView("search");
    }

    @GetMapping("/searchSimilar")
    public ModelAndView searchSimilar(Long idRoute) {
        return new ModelAndView("search");
    }

    @GetMapping("/showRoute")
    public ModelAndView showRoute(Long idRoute) {
        return new ModelAndView("search");
    }

    @PostMapping("/share")
    public void shareLink(Long idRoute) {

    }
}