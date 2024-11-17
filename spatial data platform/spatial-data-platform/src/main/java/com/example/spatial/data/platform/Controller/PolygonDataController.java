package com.example.spatial.data.platform.Controller;
import com.example.spatial.data.platform.Models.PolygonData;
import com.example.spatial.data.platform.Service.PolygonDataService;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polygons")
public class PolygonDataController {
    @Autowired
    private PolygonDataService polygonDataService;

    @GetMapping
    public List<PolygonData> getAllPolygons() {
        return polygonDataService.getAllPolygons();
    }

    @GetMapping("/{id}")
    public PolygonData getPolygonById(@PathVariable Long id) {
        return polygonDataService.getPolygonById(id)
                .orElseThrow(() -> new RuntimeException("Polygon not found with ID " + id));
    }

    @PostMapping
    public PolygonData createPolygon(@RequestBody Polygon area) {
        return polygonDataService.createPolygon(area);
    }

    @PutMapping("/{id}")
    public PolygonData updatePolygon(@PathVariable Long id, @RequestBody Polygon area) {
        return polygonDataService.updatePolygon(id, area);
    }

    @DeleteMapping("/{id}")
    public void deletePolygon(@PathVariable Long id) {
        polygonDataService.deletePolygon(id);
    }
}
