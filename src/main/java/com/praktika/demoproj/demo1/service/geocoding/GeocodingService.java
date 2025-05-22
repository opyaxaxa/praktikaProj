package com.praktika.demoproj.demo1.service.geocoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.praktika.demoproj.demo1.service.geocoding.exception.GeocodingException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GeocodingService {
    private final RestTemplate restTemplate;
    private final GeometryFactory geometryFactory;
    private final String apiKey;
    private static final String YANDEX_GEOCODE_URL = "https://geocode-maps.yandex.ru/1.x/";

    @Autowired
    public GeocodingService(
            RestTemplate restTemplate,
            GeometryFactory geometryFactory,
            @Value("${yandex.geocoder.api-key}") String apiKey // Исправлено
    ) {
        this.restTemplate = restTemplate;
        this.geometryFactory = geometryFactory;
        this.apiKey = apiKey;
    }

    @Cacheable(value = "geocodingCache", key = "#address")
    public Point geocode(String address) throws GeocodingException {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(YANDEX_GEOCODE_URL)
                    .queryParam("apikey", apiKey)
                    .queryParam("geocode", address)
                    .queryParam("format", "json")
                    .build()
                    .toUri();

            ResponseEntity<JsonNode> response = restTemplate.getForEntity(uri, JsonNode.class);
            JsonNode body = response.getBody();

            if (body == null || !(body.path("response").path("GeoObjectCollection")
                    .path("metaDataProperty").path("GeocoderResponseMetaData")
                    .path("found").asInt(0) > 0)) {
                throw new GeocodingException("Адрес не найден: " + address);
            }

            JsonNode pos = body.path("response").path("GeoObjectCollection")
                    .path("featureMember").get(0)
                    .path("GeoObject").path("Point")
                    .path("pos");

            String[] coordinates = pos.asText().split(" ");
            double longitude = Double.parseDouble(coordinates[0]);
            double latitude = Double.parseDouble(coordinates[1]);

            return geometryFactory.createPoint(new Coordinate(longitude, latitude));

        } catch (Exception e) {
            throw new GeocodingException("Ошибка геокодирования: " + e.getMessage(), e);
        }
    }
}