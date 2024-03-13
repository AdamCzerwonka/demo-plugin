package org.example.demo1;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.table.JBTable;
import org.example.demo1.model.Location;
import org.example.demo1.model.WeatherData;
import org.example.demo1.services.LocationService;
import org.example.demo1.services.WeatherService;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherContentWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        WeatherContentWindow weatherContentWindow = new WeatherContentWindow(toolWindow);
        Content content = toolWindow.getContentManager().getFactory().createContent(weatherContentWindow.getContentPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public static class WeatherContentWindow {
        private final JPanel contentPanel = new JPanel();
        private final JTextField textField = new JTextField();
        private final JButton searchButton = new JButton("Search");
        private Location location;

        public WeatherContentWindow(ToolWindow toolWindow) {
            textField.setPreferredSize(new Dimension(200, 30));
            searchButton.addActionListener(new SearchButtonListener());
            contentPanel.setLayout(new BorderLayout(0, 20));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
            JPanel searchPanel = new JPanel();
            searchPanel.add(textField);
            searchPanel.add(searchButton);
            contentPanel.add(searchPanel);
        }

        public JPanel getContentPanel() {
            return contentPanel;
        }

        class SearchButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String value = textField.getText();
                LocationService locationService = new LocationService();
                try {
                    location = locationService.getLocation(value);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Location not found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                WeatherService weatherService = new WeatherService();
                WeatherData data = weatherService.getWeather(location);

                WeatherContainer container = new WeatherContainer(data, location);
                contentPanel.add(container.getContentPanel(), BorderLayout.PAGE_END);
                contentPanel.revalidate();
            }
        }
    }
}
