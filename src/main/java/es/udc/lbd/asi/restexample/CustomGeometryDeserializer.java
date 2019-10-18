package es.udc.lbd.asi.restexample;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.locationtech.jts.geom.*;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 *
 Copyright (c) 2004, Miguel Andrade @miguelcobain
 Apache License 2.0

 Project repository: https://github.com/miguelcobain/jackson-geojson

 */

public class CustomGeometryDeserializer extends JsonDeserializer<Geometry> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public static int SRID = 4326;
    protected static final GeometryFactory geometryFactory =
        new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), SRID);

    @Override
    public Geometry deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec oc = jp.getCodec();
        JsonNode root = oc.readTree(jp);
        return parseGeometry(root);
    }

    private Geometry parseGeometry(JsonNode root) {
        String typeName = root.get("geometry") != null ? root.get("geometry").get("type").asText() : root.get("type").asText();
        JsonNode coordinates = root.get("geometry") != null ? root.get("geometry").get("coordinates") : root.get("coordinates");

        if (typeName.equals("Point")) {
            return geometryFactory.createPoint(parseCoordinate(coordinates));
        } else if(typeName.equals("LineString")) {
            return geometryFactory.createLineString(parseLineString(coordinates));
        } else if (typeName.equals("MultiLineString")) {
            return geometryFactory.createMultiLineString(parseLineStrings(coordinates));
        } else if(typeName.equals("Polygon")) {
            return parsePolygonCoordinates(coordinates);
        } else if (typeName.equals("MultiPolygon")) {
            return geometryFactory.createMultiPolygon(parsePolygons(coordinates));
        } else if (typeName.equals("GeometryCollection")) {
            return geometryFactory.createGeometryCollection(parseGeometries(coordinates));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private Geometry[] parseGeometries(JsonNode arrayOfGeoms) {
        Geometry[] items = new Geometry[arrayOfGeoms.size()];
        for(int i=0;i!=arrayOfGeoms.size();++i) {
            items[i] = parseGeometry(arrayOfGeoms.get(i));
        }
        return items;
    }

    private Polygon parsePolygonCoordinates(JsonNode arrayOfRings) {
        return geometryFactory.createPolygon(parseExteriorRing(arrayOfRings),
            parseInteriorRings(arrayOfRings));
    }

    private Polygon[] parsePolygons(JsonNode arrayOfPolygons) {
        Polygon[] polygons = new Polygon[arrayOfPolygons.size()];
        for (int i = 0; i != arrayOfPolygons.size(); ++i) {
            polygons[i] = parsePolygonCoordinates(arrayOfPolygons.get(i));
        }
        return polygons;
    }

    private LinearRing parseExteriorRing(JsonNode arrayOfRings) {
        return geometryFactory.createLinearRing(parseLineString(arrayOfRings.get(0)));
    }

    private LinearRing[] parseInteriorRings(JsonNode arrayOfRings) {
        LinearRing rings[] = new LinearRing[arrayOfRings.size() - 1];
        for (int i = 1; i < arrayOfRings.size(); ++i) {
            rings[i - 1] = geometryFactory.createLinearRing(parseLineString(arrayOfRings.get(i)));
        }
        return rings;
    }

    private Coordinate parseCoordinate(JsonNode array) {
        return new Coordinate(array.get(0).asDouble(), array.get(1).asDouble());
    }

    private Coordinate[] parseLineString(JsonNode array) {
        Coordinate[] points = new Coordinate[array.size()];
        for (int i = 0; i != array.size(); ++i) {
            points[i] = parseCoordinate(array.get(i));
        }
        return points;
    }

    private LineString[] parseLineStrings(JsonNode array) {
        LineString[] strings = new LineString[array.size()];
        for (int i = 0; i != array.size(); ++i) {
            strings[i] = geometryFactory.createLineString(parseLineString(array.get(i)));
        }
        return strings;
    }
}
