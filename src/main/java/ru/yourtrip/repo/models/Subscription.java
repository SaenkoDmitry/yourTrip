package ru.yourtrip.repo.models;

import javax.persistence.*;

@Entity
public class Subscription {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id_from")
    private Person personS;

    @ManyToOne
    @JoinColumn(name = "person_id_to")
    private Person personS2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonS() {
        return personS;
    }

    public void setPersonS(Person personS) {
        this.personS = personS;
    }

    public Person getPersonS2() {
        return personS2;
    }

    public void setPersonS2(Person personS2) {
        this.personS2 = personS2;
    }

    public Subscription() { }

    public Subscription(Person personS, Person personS2) {
        this.personS = personS;
        this.personS2 = personS2;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", personS=" + personS +
                ", personS2=" + personS2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;

        Subscription Subscription = (Subscription) o;

        return getId() != null ? getId().equals(Subscription.getId()) : Subscription.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}