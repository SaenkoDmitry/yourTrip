package ru.yourtrip.repo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.yourtrip.repo.models.*;
import ru.yourtrip.repo.repositories.*;
import ru.yourtrip.repo.utils.GoogleDataLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private ShowplaceRepository showplaceRepository;

    @Autowired
    private Showplace_from_toRepository showplace_from_toRepository;

    @Autowired
    private Route_showplace_listRepository route_showplace_listRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping(value="/routes/{id}/edit")
    public ModelAndView editRoutePage(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editroute");
        Route route = routeRepository.findOne(id);
        modelAndView.addObject("route", route);
        return modelAndView;
    }

    @PostMapping(value="/routes/{id}/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ModelAndView editRoute(@ModelAttribute Route updatedRoute, @PathVariable(value="id") Long id) {
        ModelAndView modelAndView;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String login = ((User)auth.getPrincipal()).getUsername();
            Person person = personRepository.findByLogin(login);
            try {
                Route route = routeRepository.findOne(id);
                if (person != null && route != null && person.getId().equals(route.getPersonId().getId())) {
                    updatedRoute.setId(route.getId());
                    routeRepository.save(updatedRoute);
                    modelAndView = new ModelAndView("redirect:blog/routes/{" + updatedRoute.getId() + "}");
                    modelAndView.setStatus(HttpStatus.OK);
                } else {
                    throw new RuntimeException();
                }
            } catch(RuntimeException ex) {
                System.out.println(ex);
                modelAndView = new ModelAndView("redirect:routes/{id}/edit");
                modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            modelAndView = new ModelAndView("redirect:");
            modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return modelAndView;
    }

    @GetMapping(value="/routes/create")
    public ModelAndView showRoutePage() {
        ModelAndView modelAndView = new ModelAndView("createroute");
        Route route = new Route();
        List<Showplace_person> showplaces = new ArrayList<>();
        RouteWithShwpls routeWithShwpls = new RouteWithShwpls(route, showplaces);
        modelAndView.addObject("routeWithShwpls", routeWithShwpls);
        String imageUrl = route.getRouteName();
        modelAndView.addObject("route", route);
        return modelAndView;
    }

    @GetMapping(value="/routes/create/{id}/add-images")
    public ModelAndView addImageToRoute(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView("addimagetoroute");
        Route route = routeRepository.findOne(id);
        modelAndView.addObject("route", route);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @PostMapping(value="/routes/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView createRoute(@ModelAttribute RouteWithShwpls routeWithShwpls) {
        ModelAndView modelAndView;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String login = ((User)auth.getPrincipal()).getUsername();
            Person person = personRepository.findByLogin(login);
            Route route = routeWithShwpls.getRoute();
            List <Showplace_person> showplaces = routeWithShwpls.getShowplaces();
            if (person != null) {
                route.setPersonId(personRepository.findByLogin(login));
                try {
                    routeRepository.save(route);
                    // ------------------- add info about showplaces
                    Route routeFromDb = routeRepository.findByRouteName(route.getRouteName());
                    if (routeFromDb != null) {
                        // --------------------- add to showplace table
//                        for (int i = 0; i < showplaces.size(); i++) {
//                            Showplace currShowplace = new Showplace();
//                            currShowplace.setShowplaceName(showplaces.get(i).getShowplace_name());
//
//                            Showplace oldShowplace = showplaceRepository.findByShowplaceName(currShowplace.getShowplaceName());
//                            if (oldShowplace == null) {
//                                currShowplace.setCoords(GoogleDataLoader.getCoords(currShowplace.getShowplaceName()));
//                                currShowplace.setNum_of_marks(1);
//                                showplaceRepository.save(currShowplace);
//                            } else {
//                                oldShowplace.setNum_of_marks(oldShowplace.getNum_of_marks() + 1);
//                                oldShowplace.setAvg_mark((oldShowplace.getAvg_mark() + showplaces.get(i).getMark()) / oldShowplace.getNum_of_marks());
//                            }
//
//                        }
//                        // ----------------------------------------------
//                        for (int i = 0; i < showplaces.size(); i++) {
//                            // add to route_showplace_list table
//                            Showplace fromShowplace = showplaceRepository.findByShowplaceName(showplaces.get(i).getShowplace_name());
//                            Route_showplace_list route_showplace_list = new Route_showplace_list();
//                            route_showplace_list.setIndex_number(i);
//                            route_showplace_list.setPersonL(person);
//                            route_showplace_list.setRouteL(routeFromDb);
//                            route_showplace_list.setShowplaceL(fromShowplace);
//                            route_showplace_list.setShowplace_mark(showplaces.get(i).getMark()); // that's mean actually showplaces.get(i)
//                            route_showplace_list.setVisit_date(showplaces.get(i).getVisit_date());
//                            route_showplace_listRepository.save(route_showplace_list);
//                            if (i == showplaces.size() - 1) continue;
//
//                            // add to showplace_from_to table
//                            Showplace toShowplace = showplaceRepository.findByShowplaceName(showplaces.get(i + 1).getShowplace_name());
//                            Showplace_from_to showplace_from_to = new Showplace_from_to();
//                            showplace_from_to.setPersonFt(person);
//                            showplace_from_to.setRouteFt(routeFromDb);
//                            showplace_from_to.setDistance(GoogleDataLoader.getDistance(fromShowplace.getShowplaceName(),
//                                    toShowplace.getShowplaceName()));
//                            showplace_from_to.setSpent_time(GoogleDataLoader.getSpentTime(fromShowplace.getShowplaceName(),
//                                    toShowplace.getShowplaceName()));
//                            showplace_from_to.setShowplaceFrom(fromShowplace);
//                            showplace_from_to.setShowplaceTo(toShowplace);
//                            showplace_from_toRepository.save(showplace_from_to);
//
//                        }
                    } else {
                        // Something goes wrong
                    }
                    // --------------------
                    modelAndView = new ModelAndView("redirect:/blog/routes/create/" + routeFromDb.getId()  + "/add-images");
                    modelAndView.setStatus(HttpStatus.OK);
                } catch (RuntimeException ex) {
                    System.out.println(ex);
                    modelAndView = new ModelAndView("redirect:");
                    modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                modelAndView = new ModelAndView("redirect:");
                modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            }
        } else {
            modelAndView = new ModelAndView("redirect:");
            modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return modelAndView;
    }

    @GetMapping("/routes/{id}")
    public ModelAndView showRoute(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView = new ModelAndView("route");
        Route route = routeRepository.findOne(id);
        modelAndView.addObject("route", route);
        ArrayList<Image> imgUrls = imageRepository.findByRouteId(route);
        modelAndView.addObject("imgUrls", imgUrls);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }

    @DeleteMapping(value="/routes/{id}")
    public ModelAndView deleteRoute(@PathVariable(value="id") Long id) {
        ModelAndView modelAndView;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String login = ((User)auth.getPrincipal()).getUsername();
            Person person = personRepository.findByLogin(login);
            try {
                Route route = routeRepository.findOne(id);
                if (person != null && route != null && person.getId().equals(route.getPersonId().getId())) {
                    routeRepository.delete(id);
                    modelAndView = new ModelAndView("redirect:/");
                    modelAndView.setStatus(HttpStatus.OK);
                } else {
                    modelAndView = new ModelAndView("redirect:/");
                    modelAndView.setStatus(HttpStatus.BAD_REQUEST);
                }
            } catch(RuntimeException ex) {
                System.out.println(ex);
                modelAndView = new ModelAndView("redirect:/");
                modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            modelAndView = new ModelAndView("redirect:/");
            modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return modelAndView;
    }

    @PostMapping(value="/subscription/{idFrom}/{idTo}")
    public ResponseEntity subscribeToPerson(@PathVariable(value="id") Long idFrom, @PathVariable(value="id") Long idTo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String login = ((User) auth.getPrincipal()).getUsername();
            try {
                Person person = personRepository.findByLogin(login);
                Person person1 = personRepository.findOne(idTo);
                if (person != null && person1 != null && person.getId().equals(idFrom)) {
                    Subscription subscription = new Subscription();
                    subscription.setPersonS(person);
                    subscription.setPersonS2(person1);
                    subscriptionRepository.save(subscription);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    // Something goes wrong
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
            } catch(RuntimeException ex) {
                System.out.println(ex);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value="/subscription/{idFrom}/{idTo}")
    public ResponseEntity unsubscribeFromPerson(@PathVariable(value="id") Long idFrom, @PathVariable(value="id") Long idTo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth.getPrincipal()).equals("anonymousUser")) {
            String login = ((User) auth.getPrincipal()).getUsername();
            try {
                Person person = personRepository.findByLogin(login);
                Person person1 = personRepository.findOne(idTo);
                Subscription subscription = subscriptionRepository.getByPersonSAndPersonS2(person, person1);
                if (person != null && person1 != null && person.getId().equals(idFrom)) {
                    subscriptionRepository.delete(subscription.getId());
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    // Something goes wrong
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                }
            } catch(RuntimeException ex) {
                System.out.println(ex);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value="/search")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView("searcher");
        return modelAndView;
    }

//    @PostMapping(value="/image", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif", produces = "image/jpg")
//    public ResponseBody getImage() {
//        ModelAndView modelAndView = new ModelAndView("image");
//        return modelAndView;
//    }
}