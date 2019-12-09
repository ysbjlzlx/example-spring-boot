package com.anydong.example.springboot;

import org.geotools.geojson.geom.GeometryJSON;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.StringWriter;

@SpringBootTest
public class GeoJsonTests {
    @Test
    public void pointToGeometryJson() throws IOException {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(113, 22));

        StringWriter stringWriter = new StringWriter();
        GeometryJSON geometryJSON = new GeometryJSON();
        geometryJSON.writePoint(point, stringWriter);
        System.out.println(stringWriter.toString());
    }
}
