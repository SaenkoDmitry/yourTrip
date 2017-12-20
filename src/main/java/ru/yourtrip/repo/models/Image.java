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
    private Route routeId;

    public Image() { }

    public Image(String url, Route routeId) {
        this.url = url;
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", routeId=" + routeId +
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

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }
}