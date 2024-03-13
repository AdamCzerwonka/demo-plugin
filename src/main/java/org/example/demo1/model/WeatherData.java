package org.example.demo1.model;

import org.jetbrains.annotations.Nls;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherData implements TableModel {
    public double latitude;
    public double longitude;
    public double generationtime_ms;
    public int utc_offset_seconds;
    public String timezone;
    public String timezone_abbreviation;
    public int elevation;
    public Current current;
    public Hourly hourly;

    @Override
    public int getRowCount() {
        return hourly.time.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Nls
    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Time";
            case 1 -> "Temperature";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            Calendar calendar = hourly.time.get(rowIndex);
            LocalDateTime time = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
            return time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return hourly.temperature_2m.get(rowIndex) + "\u00B0C";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public class Hourly {
        public List<Calendar> time;
        public List<Double> temperature_2m;
    }

    public class Current {
        public String time;
        public int interval;
        public double temperature_2m;
        public int relative_humidity_2m;
        public int wind_speed_10m;
    }
}
