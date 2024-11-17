package com.example.spatial.data.platform.Repositories;

import com.example.spatial.data.platform.Models.PointData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PointDataRepository extends JpaRepository<PointData, Long> {
    // Find all points within a certain distance from a given point
    @Query(value = "SELECT * FROM point_data " +
            "WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :distance)",
            nativeQuery = true)
    List<PointData> findPointsWithinDistance(
            @Param("longitude") double longitude,
            @Param("latitude") double latitude,
            @Param("distance") double distance);

    // Find the closest point to a given location
    @Query(value = "SELECT * FROM point_data " +
            "ORDER BY location <-> ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326) " +
            "LIMIT 1",
            nativeQuery = true)
    PointData findClosestPoint(
            @Param("longitude") double longitude,
            @Param("latitude") double latitude);

    // Find points within a bounding box (rectangle)
    @Query(value = "SELECT * FROM point_data " +
            "WHERE ST_Within(location, ST_MakeEnvelope(:xmin, :ymin, :xmax, :ymax, 4326))",
            nativeQuery = true)
    List<PointData> findPointsWithinBoundingBox(
            @Param("xmin") double xmin,
            @Param("ymin") double ymin,
            @Param("xmax") double xmax,
            @Param("ymax") double ymax);
}
