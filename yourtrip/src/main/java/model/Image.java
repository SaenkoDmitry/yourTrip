package model;

import javax.persistence.*;

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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