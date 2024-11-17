package com.example.spatial.data.platform.Controller;
import com.example.spatial.data.platform.Models.PointData;
import com.example.spatial.data.platform.Service.PointDataService;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/points")
public class PointDataController {
    @Autowired
    private PointDataService pointDataService;

    @GetMapping
    public List<PointData> getAllPoints() {
        return pointDataService.getAllPoints();
    }

    @GetMapping("/{id}")
    public PointData getPointById(@PathVariable Long id) {
        return pointDataService.getPointById(id)
                .orElseThrow(() -> new RuntimeException("Point not found with ID " + id));
    }

    @PostMapping
    public PointData createPoint(@RequestBody Point location) {
        return pointDataService.createPoint(location);
    }

    @PutMapping("/{id}")
    public PointData updatePoint(@PathVariable Long id, @RequestBody Point location) {
        return pointDataService.updatePoint(id, location);
    }

    @DeleteMapping("/{id}")
    public void deletePoint(@PathVariable Long id) {
        pointDataService.deletePoint(id);
    }
}
