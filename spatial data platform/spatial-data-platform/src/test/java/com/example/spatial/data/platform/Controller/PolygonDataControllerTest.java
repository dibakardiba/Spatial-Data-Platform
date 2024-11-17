package com.example.spatial.data.platform.Controller;
import com.example.spatial.data.platform.Models.PolygonData;
import com.example.spatial.data.platform.Service.PolygonDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class PolygonDataControllerTest {
    @Mock
    private PolygonDataService polygonDataService;

    @InjectMocks
    private PolygonDataController polygonDataController;

    private MockMvc mockMvc;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    public void testGetAllPolygons() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(polygonDataController).build();

        LinearRing shell = geometryFactory.createLinearRing(new Coordinate[]{
                new Coordinate(10, 10), new Coordinate(20, 10), new Coordinate(20, 20), new Coordinate(10, 10)
        });
        Polygon polygon1 = geometryFactory.createPolygon(shell, null);

        PolygonData data1 = new PolygonData();
        data1.setId(1L);
        data1.setArea(polygon1);

        List<PolygonData> polygonList = Arrays.asList(data1);

        when(polygonDataService.getAllPolygons()).thenReturn(polygonList);

        mockMvc.perform(get("/api/polygons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void testCreatePolygon() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(polygonDataController).build();

        LinearRing shell = geometryFactory.createLinearRing(new Coordinate[]{
                new Coordinate(10, 10), new Coordinate(20, 10), new Coordinate(20, 20), new Coordinate(10, 10)
        });
        Polygon polygon = geometryFactory.createPolygon(shell, null);

        PolygonData data = new PolygonData();
        data.setId(1L);
        data.setArea(polygon);

        when(polygonDataService.createPolygon(Mockito.any(Polygon.class))).thenReturn(data);

        mockMvc.perform(post("/api/polygons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\": \"Polygon\", \"coordinates\": [[[10, 10], [20, 10], [20, 20], [10, 10]]]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
