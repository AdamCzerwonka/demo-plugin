package org.example.demo1.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo1.model.Location;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class LocationService {
    private final static String API_KEY = "5742b11caf8e303b9034acf28083e2fe";

    public Location getLocation(String cityName) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&appid=" + API_KEY))
                .GET()
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Location> locations = mapper.readValue(response.body(), new TypeReference<>() {
            });

            return locations.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
