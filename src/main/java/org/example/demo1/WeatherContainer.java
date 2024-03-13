package org.example.demo1;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import org.example.demo1.model.Location;
import org.example.demo1.model.WeatherData;

import javax.swing.*;
import java.awt.*;

public class WeatherContainer {
    private final JPanel contentPanel = new JPanel();

    public WeatherContainer(WeatherData data, Location location) {
        LayoutManager manager = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setLayout(manager);
        JLabel label = new JLabel("Weather Data for: " + location.name);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        contentPanel.add(label, BorderLayout.PAGE_START);
        contentPanel.add(new JLabel("Current weather"));
        contentPanel.add(new JLabel("Temperature: " + data.current.temperature_2m + "\u00B0C"));
        contentPanel.add(new JLabel("Humidity: " + data.current.relative_humidity_2m + "%"));
        contentPanel.add(new JLabel("Wind speed: " + data.current.wind_speed_10m + "km/h"));
        JBTable table = new JBTable(data);
        JBScrollPane scrollPane = new JBScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 510));
        contentPanel.add(scrollPane);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
