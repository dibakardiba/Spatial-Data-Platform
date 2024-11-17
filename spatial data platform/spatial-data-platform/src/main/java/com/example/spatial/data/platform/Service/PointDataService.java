package com.example.spatial.data.platform.Service;
import com.example.spatial.data.platform.Models.PointData;
import com.example.spatial.data.platform.Repositories.PointDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Point;

import java.util.List;
import java.util.Optional;

@Service
public class PointDataService {
    @Autowired
    private PointDataRepository pointDataRepository;

    // Get all points
    public List<PointData> getAllPoints() {
        return pointDataRepository.findAll();
    }

    // Get a single point by ID
    public Optional<PointData> getPointById(Long id) {
        return pointDataRepository.findById(id);
    }

    // Create a new point
    public PointData createPoint(Point location) {
        PointData pointData = new PointData();
        pointData.setLocation(location);
        return pointDataRepository.save(pointData);
    }

    // Update an existing point
    public PointData updatePoint(Long id, Point location) {
        Optional<PointData> existingPointData = pointDataRepository.findById(id);
        if (existingPointData.isPresent()) {
            PointData pointData = existingPointData.get();
            pointData.setLocation(location);
            return pointDataRepository.save(pointData);
        } else {
            throw new RuntimeException("Point with ID " + id + " not found.");
        }
    }

    // Delete a point
    public void deletePoint(Long id) {
        pointDataRepository.deleteById(id);
    }
}
