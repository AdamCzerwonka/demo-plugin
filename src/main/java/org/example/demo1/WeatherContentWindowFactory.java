package org.example.demo1;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
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
        private final JLabel text = new JLabel("Hello, World!");
        private final SearchBar searchBar = new SearchBar();

        public WeatherContentWindow(ToolWindow toolWindow) {
            contentPanel.setLayout(new BorderLayout(0, 20));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
            contentPanel.add(text, BorderLayout.PAGE_START);
            contentPanel.add(searchBar.getContentPanel());
        }

        public JPanel getContentPanel() {
            return contentPanel;
        }
    }
}
