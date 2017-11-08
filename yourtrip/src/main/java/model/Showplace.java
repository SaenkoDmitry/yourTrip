package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Showplace", schema = "yourtrip")
public class Showplace implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "showplace_name", unique = true, nullable = false, length = 100)
    private String showplace_name;

    @Column(name = "coords", nullable = false, length = 50)
    private String coords;

    @Column(name = "avg_mark", nullable = false)
    private Double avg_mark;

    @Column(name = "num_of_marks", nullable = false)
    private Integer num_of_marks;

    @Column(name = "visit_date", nullable = false)
    private Date visit_date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="showplaceL")
    private Set<Route_showplace_list> route_showplace_listsS;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="showplaceFt")
    private Set<Showplace_from_to> showplace_from_tosS1;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="showplaceFt2")
    private Set<Showplace_from_to> showplace_from_tosS2;

    public Showplace() { }

    public Showplace(String showplace_name, String coords, Double avg_mark, Integer num_of_marks, Date visit_date) {
        this.showplace_name = showplace_name;
        this.coords = coords;
        this.avg_mark = avg_mark;
        this.num_of_marks = num_of_marks;
        this.visit_date = visit_date;
    }

    @Override
    public String toString() {
        return "Showplace{" +
                "id=" + id +
                ", showplace_name='" + showplace_name + '\'' +
                ", coords='" + coords + '\'' +
                ", avg_mark=" + avg_mark +
                ", num_of_marks=" + num_of_marks +
                ", visit_date=" + visit_date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShowplace_name() {
        return showplace_name;
    }

    public void setShowplace_name(String showplace_name) {
        this.showplace_name = showplace_name;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }

    public Double getAvg_mark() {
        return avg_mark;
    }

    public void setAvg_mark(Double avg_mark) {
        this.avg_mark = avg_mark;
    }

    public Integer getNum_of_marks() {
        return num_of_marks;
    }

    public void setNum_of_marks(Integer num_of_marks) {
        this.num_of_marks = num_of_marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Showplace)) return false;

        Showplace showplace = (Showplace) o;

        if (getId() != null ? !getId().equals(showplace.getId()) : showplace.getId() != null) return false;
        if (getShowplace_name() != null ? !getShowplace_name().equals(showplace.getShowplace_name()) : showplace.getShowplace_name() != null)
            return false;
        return getCoords() != null ? getCoords().equals(showplace.getCoords()) : showplace.getCoords() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getShowplace_name() != null ? getShowplace_name().hashCode() : 0);
        result = 31 * result + (getCoords() != null ? getCoords().hashCode() : 0);
        return result;
    }
}