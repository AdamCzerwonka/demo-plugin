package org.example.demo1.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo1.model.Location;
import org.example.demo1.model.WeatherData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {
    public WeatherData getWeather(Location location) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.open-meteo.com/v1/forecast?latitude=" + location.lat + "&longitude=" + location.lon + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m&hourly=temperature_2m&timezone=Europe%2FBerlin&forecast_days=1"))
                .GET()
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), WeatherData.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
