package org.example.demo1;

import org.example.demo1.model.Location;
import org.example.demo1.model.WeatherData;
import org.example.demo1.services.LocationService;
import org.example.demo1.services.WeatherService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBar {
    private final JPanel contentPanel = new JPanel();
    private final JTextField textField = new JTextField();
    private final JButton searchButton = new JButton("Search");

    public SearchBar() {
        searchButton.addActionListener(new SearchButtonListener());
        contentPanel.add(textField);
        contentPanel.add(searchButton);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String value = textField.getText();
            LocationService locationService = new LocationService();
            Location location = locationService.getLocation(value);
            WeatherService weatherService = new WeatherService();
            WeatherData data = weatherService.getWeather(location);

            System.out.println(data.hourly.temperature_2m.get(0));
        }
    }
}
