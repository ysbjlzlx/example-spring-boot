package com.anydong.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author anydong
 */
@RestController
@RequestMapping(value = "/place", produces = "application/json; charset=utf-8")
public class PlaceController {
    @GetMapping
    public Object getGeoJson() throws IOException {

        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(113, 22));

        StringWriter stringWriter = new StringWriter();
        GeometryJSON geometryJSON = new GeometryJSON();
        geometryJSON.writePoint(point, stringWriter);
        return JSON.parse(stringWriter.toString());
    }
}
