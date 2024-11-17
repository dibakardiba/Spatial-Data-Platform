package com.example.spatial.data.platform.Service;
import com.example.spatial.data.platform.Models.PolygonData;
import com.example.spatial.data.platform.Repositories.PolygonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Polygon;

import java.util.List;
import java.util.Optional;
@Service
public class PolygonDataService {
    @Autowired
    private PolygonDataRepository polygonDataRepository;

    // Get all polygons
    public List<PolygonData> getAllPolygons() {
        return polygonDataRepository.findAll();
    }

    // Get a single polygon by ID
    public Optional<PolygonData> getPolygonById(Long id) {
        return polygonDataRepository.findById(id);
    }

    // Create a new polygon
    public PolygonData createPolygon(Polygon area) {
        PolygonData polygonData = new PolygonData();
        polygonData.setArea(area);
        return polygonDataRepository.save(polygonData);
    }

    // Update an existing polygon
    public PolygonData updatePolygon(Long id, Polygon area) {
        Optional<PolygonData> existingPolygonData = polygonDataRepository.findById(id);
        if (existingPolygonData.isPresent()) {
            PolygonData polygonData = existingPolygonData.get();
            polygonData.setArea(area);
            return polygonDataRepository.save(polygonData);
        } else {
            throw new RuntimeException("Polygon with ID " + id + " not found.");
        }
    }

    // Delete a polygon
    public void deletePolygon(Long id) {
        polygonDataRepository.deleteById(id);
    }
}

