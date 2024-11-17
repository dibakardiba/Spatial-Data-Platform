package com.example.spatial.data.platform.Repositories;

import com.example.spatial.data.platform.Models.PolygonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PolygonDataRepository extends JpaRepository<PolygonData, Long> {
    // Find all polygons that contain a specific point
    @Query(value = "SELECT * FROM polygon_data " +
            "WHERE ST_Contains(area, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326))",
            nativeQuery = true)
    List<PolygonData> findPolygonsContainingPoint(
            @Param("longitude") double longitude,
            @Param("latitude") double latitude);

    // Find polygons that intersect a given polygon
    @Query(value = "SELECT * FROM polygon_data " +
            "WHERE ST_Intersects(area, ST_GeomFromText(:polygonWkt, 4326))",
            nativeQuery = true)
    List<PolygonData> findPolygonsIntersecting(
            @Param("polygonWkt") String polygonWkt);

    // Find polygons within a bounding box
    @Query(value = "SELECT * FROM polygon_data " +
            "WHERE ST_Within(area, ST_MakeEnvelope(:xmin, :ymin, :xmax, :ymax, 4326))",
            nativeQuery = true)
    List<PolygonData> findPolygonsWithinBoundingBox(
            @Param("xmin") double xmin,
            @Param("ymin") double ymin,
            @Param("xmax") double xmax,
            @Param("ymax") double ymax);
}
