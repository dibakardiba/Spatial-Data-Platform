package com.example.spatial.data.platform.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Polygon;

@Entity
@Table(name = "polygon_data")
public class PolygonData {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, columnDefinition = "geometry(Polygon,4326)")
   private Polygon area;

   // Getters and Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Polygon getArea() {
      return area;
   }

   public void setArea(Polygon area) {
      this.area = area;
   }
}
