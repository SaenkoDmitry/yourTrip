package ru.yourtrip.repo.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Image implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "url", unique = true, nullable = false, length = 200)
    private String url;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route routeI;

    public Image() { }

    public Image(String url, Route routeI) {
        this.url = url;
        this.routeI = routeI;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", routeI=" + routeI +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Route getRouteI() {
        return routeI;
    }

    public void setRouteI(Route routeI) {
        this.routeI = routeI;
    }
}