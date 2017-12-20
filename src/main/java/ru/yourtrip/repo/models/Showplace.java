package ru.yourtrip.repo.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Showplace implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "showplace_name", unique = true, nullable = false, length = 100)
    private String showplaceName;

    @Column(name = "coords", nullable = false, length = 50)
    private String coords;

    @Column(name = "avg_mark", nullable = false)
    private Double avg_mark;

    @Column(name = "num_of_marks", nullable = false)
    private Integer num_of_marks;

    public Showplace() { }

    public Showplace(String showplace_name, String coords, Double avg_mark, Integer num_of_marks, Date visit_date) {
        this.showplaceName = showplace_name;
        this.coords = coords;
        this.avg_mark = avg_mark;
        this.num_of_marks = num_of_marks;
    }

    @Override
    public String toString() {
        return "Showplace{" +
                "id=" + id +
                ", showplaceName='" + showplaceName + '\'' +
                ", coords='" + coords + '\'' +
                ", avg_mark=" + avg_mark +
                ", num_of_marks=" + num_of_marks +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowplaceName() {
        return showplaceName;
    }

    public void setShowplaceName(String showplace_name) {
        this.showplaceName = showplace_name;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
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
        if (getShowplaceName() != null ? !getShowplaceName().equals(showplace.getShowplaceName()) : showplace.getShowplaceName() != null)
            return false;
        return getCoords() != null ? getCoords().equals(showplace.getCoords()) : showplace.getCoords() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getShowplaceName() != null ? getShowplaceName().hashCode() : 0);
        result = 31 * result + (getCoords() != null ? getCoords().hashCode() : 0);
        return result;
    }
}