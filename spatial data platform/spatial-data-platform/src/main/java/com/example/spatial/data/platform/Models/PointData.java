package com.example.spatial.data.platform.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;
@Entity
@Table(name = "point_data")
public class PointData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "geometry(Point,4326)")
    private Point location;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
