package ru.yourtrip.repo.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PersonRouteShowplaceId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person personL;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route routeL;

    @ManyToOne
    @JoinColumn(name = "showplace_id")
    private Showplace showplaceL;

    public Person getPersonL() {
        return personL;
    }

    public void setPersonL(Person personL) {
        this.personL = personL;
    }

    public Route getRouteL() {
        return routeL;
    }

    public void setRouteL(Route routeL) {
        this.routeL = routeL;
    }

    public Showplace getShowplaceL() {
        return showplaceL;
    }

    public void setShowplaceL(Showplace showplaceL) {
        this.showplaceL = showplaceL;
    }
}