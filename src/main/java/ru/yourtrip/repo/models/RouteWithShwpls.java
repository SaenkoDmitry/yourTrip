package ru.yourtrip.repo.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class RouteWithShwpls implements Serializable {
    private Route route;
    private List<Showplace_person> showplaces;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Showplace_person> getShowplaces() {
        return showplaces;
    }

    public void setShowplaces(List<Showplace_person> showplaces) {
        this.showplaces = showplaces;
    }

    public RouteWithShwpls() { }

    public RouteWithShwpls(Route route, List<Showplace_person> showplaces) {
        this.route = route;
        this.showplaces = showplaces;
    }

    @Override
    public String toString() {
        return "RouteWithShwpls{" +
                "route=" + route +
                ", showplaces=" + showplaces +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteWithShwpls that = (RouteWithShwpls) o;
        return Objects.equals(route, that.route);
    }

    @Override
    public int hashCode() {

        return Objects.hash(route);
    }
}