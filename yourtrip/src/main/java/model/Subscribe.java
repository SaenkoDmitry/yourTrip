package model;

import javax.persistence.*;

@Entity
@Table(name = "Subscribe", schema = "yourtrip")
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id_from")
    private Person personS;

    @ManyToOne
    @JoinColumn(name = "person_id_to")
    private Person personS2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Subscribe() { }

    public Subscribe(Person personS, Person personS2) {
        this.personS = personS;
        this.personS2 = personS2;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", personS=" + personS +
                ", personS2=" + personS2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscribe)) return false;

        Subscribe subscribe = (Subscribe) o;

        return getId() != null ? getId().equals(subscribe.getId()) : subscribe.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}