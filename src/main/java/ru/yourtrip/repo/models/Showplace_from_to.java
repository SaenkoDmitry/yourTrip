package ru.yourtrip.repo.models;

import org.hibernate.result.ResultSetOutput;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
public class Showplace_from_to implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person personFt;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private Person person;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route routeFt;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private Route route;

    @Column(name = "spent_time", nullable = false)
    private Time spent_time;

    @Column(name = "distance", nullable = false)
    private Integer distance;

    @ManyToOne
    @JoinColumn(name = "showplace_from_id")
    private Showplace showplaceFrom;

    @ManyToOne
    @JoinColumn(name = "showplace_to_id")
    private Showplace showplaceTo;

    public Showplace_from_to() { }

    public Showplace_from_to(Person personFt, Route routeFt, Time spent_time, Integer distance, Showplace showplaceFrom, Showplace showplaceTo) {
        this.personFt = personFt;
        this.routeFt = routeFt;
        this.spent_time = spent_time;
        this.distance = distance;
        this.showplaceFrom = showplaceFrom;
        this.showplaceTo = showplaceTo;
    }

    @Override
    public String toString() {
        return "Showplace_from_to{" +
                "id=" + id +
                ", personFt=" + personFt +
                ", routeFt=" + routeFt +
                ", spent_time=" + spent_time +
                ", distance=" + distance +
                ", showplaceFrom=" + showplaceFrom +
                ", showplaceTo=" + showplaceTo +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonFt() {
        return personFt;
    }

    public void setPersonFt(Person personFt) {
        this.personFt = personFt;
    }

    public Route getRouteFt() {
        return routeFt;
    }

    public void setRouteFt(Route routeFt) {
        this.routeFt = routeFt;
    }

    public Time getSpent_time() {
        return spent_time;
    }

    public void setSpent_time(Time spent_time) {
        this.spent_time = spent_time;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Showplace getShowplaceFrom() {
        return showplaceFrom;
    }

    public void setShowplaceFrom(Showplace showplaceFrom) {
        this.showplaceFrom = showplaceFrom;
    }

    public Showplace getShowplaceTo() {
        return showplaceTo;
    }

    public void setShowplaceTo(Showplace showplaceTo) {
        this.showplaceTo = showplaceTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Showplace_from_to)) return false;

        Showplace_from_to that = (Showplace_from_to) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}