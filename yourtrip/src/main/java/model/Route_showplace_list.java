package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Route_showplace_list", schema = "yourtrip")
public class Route_showplace_list implements Serializable {
    @EmbeddedId
    private PersonRouteShowplaceId id;

    @Column(name = "index_number", nullable = false)
    private Integer index_number;

    public Route_showplace_list() { }

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person personL;

    @ManyToOne
    @JoinColumn(name = "route_id", insertable = false, updatable = false)
    private Route routeL;

    @ManyToOne
    @JoinColumn(name = "showplace_id", insertable = false, updatable = false)
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

//    public Route_showplace_list(PersonRouteShowplaceId id, Integer index_number) {
//        this.id = id;
//        this.index_number = index_number;
//    }


    public Route_showplace_list(PersonRouteShowplaceId id, Integer index_number, Person personL, Route routeL, Showplace showplaceL) {
        this.id = id;
        this.index_number = index_number;
        this.personL = personL;
        this.routeL = routeL;
        this.showplaceL = showplaceL;
    }

    @Override
    public String toString() {
        return "Route_showplace_list{" +
                "id=" + id +
                ", index_number=" + index_number +
                '}';
    }

    public PersonRouteShowplaceId getId() {
        return id;
    }

    public void setId(PersonRouteShowplaceId id) {
        this.id = id;
    }

    public Integer getIndex_number() {
        return index_number;
    }

    public void setIndex_number(Integer index_number) {
        this.index_number = index_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route_showplace_list)) return false;

        Route_showplace_list that = (Route_showplace_list) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}