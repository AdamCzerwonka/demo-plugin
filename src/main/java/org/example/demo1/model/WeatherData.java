package org.example.demo1.model;

import java.util.List;

public class WeatherData {
    public double latitude;
    public double longitude;
    public double generationtime_ms;
    public int utc_offset_seconds;
    public String timezone;
    public String timezone_abbreviation;
    public int elevation;
    public HourlyUnits hourly_units;
    public Hourly hourly;

    public class HourlyUnits {
        public String time;
        public String temperature_2m;
    }

    public class Hourly {
        public List<String> time;
        public List<Double> temperature_2m;
    }
}
