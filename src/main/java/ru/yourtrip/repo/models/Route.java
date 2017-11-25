package ru.yourtrip.repo.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Route implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "route_name", unique = true, nullable = false, length = 100)
    private String route_name;

    @Column(name = "commentary", length = 1000)
    private String commentary;

    @Column(name = "complete", nullable = false)
    private Boolean complete;

    @Column(name = "hidden", nullable = false)
    private Boolean hidden;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "mark", nullable = false)
    private Double mark;

    @Column(name = "lower_price", nullable = false)
    private Integer lowerPrice;

    @Column(name = "upper_price", nullable = false)
    private Integer upperPrice;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person personId;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="routeFt")
//    private Set<Showplace_from_to> showplace_from_tosR;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="routeL")
//    private Set<Route_showplace_list> route_showplace_listsR;

    public Route() { }

    public Route(String route_name, String commentary, Boolean complete, Boolean hidden, String category, Double mark, Integer lowerPrice, Integer upperPrice, Person personId) {
        this.route_name = route_name;
        this.commentary = commentary;
        this.complete = complete;
        this.hidden = hidden;
        this.category = category;
        this.mark = mark;
        this.lowerPrice = lowerPrice;
        this.upperPrice = upperPrice;
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", route_name='" + route_name + '\'' +
                ", commentary='" + commentary + '\'' +
                ", complete=" + complete +
                ", hidden=" + hidden +
                ", category='" + category + '\'' +
                ", mark=" + mark +
                ", lowerPrice=" + lowerPrice +
                ", upperPrice=" + upperPrice +
                ", personId=" + personId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Integer getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(Integer lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public Integer getUpperPrice() {
        return upperPrice;
    }

    public void setUpperPrice(Integer upperPrice) {
        this.upperPrice = upperPrice;
    }

    //    public Set<Showplace_from_to> getShowplace_from_tosR() {
//        return showplace_from_tosR;
//    }
//
//    public void setShowplace_from_tosR(Set<Showplace_from_to> showplace_from_tosR) {
//        this.showplace_from_tosR = showplace_from_tosR;
//    }
//
//    public Set<Route_showplace_list> getRoute_showplace_listsR() {
//        return route_showplace_listsR;
//    }
//
//    public void setRoute_showplace_listsR(Set<Route_showplace_list> route_showplace_listsR) {
//        this.route_showplace_listsR = route_showplace_listsR;
//    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (getId() != null ? !getId().equals(route.getId()) : route.getId() != null) return false;
        return getRoute_name() != null ? getRoute_name().equals(route.getRoute_name()) : route.getRoute_name() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getRoute_name() != null ? getRoute_name().hashCode() : 0);
        return result;
    }
}