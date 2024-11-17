package com.example.spatial.data.platform.Controller;
import com.example.spatial.data.platform.Models.PointData;
import com.example.spatial.data.platform.Service.PointDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
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
public class PointDataControllerTest {
    @Mock
    private PointDataService pointDataService;

    @InjectMocks
    private PointDataController pointDataController;

    private MockMvc mockMvc;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    public void testGetAllPoints() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(pointDataController).build();

        Point point1 = geometryFactory.createPoint(new Coordinate(125.6, 10.1));
        Point point2 = geometryFactory.createPoint(new Coordinate(130.6, 15.1));
        PointData data1 = new PointData();
        data1.setId(1L);
        data1.setLocation(point1);
        PointData data2 = new PointData();
        data2.setId(2L);
        data2.setLocation(point2);

        List<PointData> pointList = Arrays.asList(data1, data2);

        when(pointDataService.getAllPoints()).thenReturn(pointList);

        mockMvc.perform(get("/api/points"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].location.coordinates[0]").value(125.6))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testCreatePoint() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(pointDataController).build();

        Point point = geometryFactory.createPoint(new Coordinate(125.6, 10.1));
        PointData data = new PointData();
        data.setId(1L);
        data.setLocation(point);

        //when(pointDataService.createPoint(125.6, 10.1)).thenReturn(data);

        mockMvc.perform(post("/api/points")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"longitude\": 125.6, \"latitude\": 10.1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.location.coordinates[0]").value(125.6));
    }
}
