package ru.yourtrip.repo.models;

import java.io.Serializable;
import java.util.Date;

public class Showplace_person implements Serializable {
    private String showplace_name;
    private Double mark;
    private Date visit_date;

    public Showplace_person(String showplace_name, Double mark, Date visit_date) {
        this.showplace_name = showplace_name;
        this.mark = mark;
        this.visit_date = visit_date;
    }

    @Override
    public String toString() {
        return "Showplace_person{" +
                "showplace_name='" + showplace_name + '\'' +
                ", mark=" + mark +
                ", visit_date=" + visit_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Showplace_person that = (Showplace_person) o;

        return showplace_name != null ? showplace_name.equals(that.showplace_name) : that.showplace_name == null;
    }

    @Override
    public int hashCode() {
        return showplace_name != null ? showplace_name.hashCode() : 0;
    }

    public String getShowplace_name() {
        return showplace_name;
    }

    public void setShowplace_name(String showplace_name) {
        this.showplace_name = showplace_name;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }
}
