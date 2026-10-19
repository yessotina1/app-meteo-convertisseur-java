/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Essotina
 */
public class WeatherPanel extends JPanel{
    private JTextField cityField;
    private JButton searchButton;
    private JLabel temperatureLabel;
    private JLabel conditionLabel;
    private JLabel humidityLabel;
    private JLabel windLabel;
    private JLabel cityNameLabel;
    private JLabel feelingLabel;
    private JLabel iconLabel;

    public WeatherPanel() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            "Météo en Temps Réel"
        ));
        this.setBackground(new Color(240, 248, 255));

        initComponents();
    }

    private void initComponents(){
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        topPanel.setBackground(this.getBackground());

        JLabel cityLabel = new JLabel("Ville :");
        cityLabel.setFont(new Font("Arial", Font.BOLD, 18));

        this.cityField = new JTextField(15);
        this.cityField.setText("Lomé");
        this.cityField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.cityField.setPreferredSize(new Dimension(250, 40));

        this.searchButton = new JButton("Rechercher");
        this.searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.searchButton.setPreferredSize(new Dimension(160, 40));
        this.searchButton.setBackground(new Color(70, 130, 180));
        this.searchButton.setForeground(Color.WHITE);

        topPanel.add(cityLabel);
        topPanel.add(this.cityField);
        topPanel.add(this.searchButton);
        
        
        JPanel mainPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        mainPanel.setBackground(this.getBackground());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.cityNameLabel = createWeatherLabel("", Font.BOLD, 22, new Color(25, 25, 112));
        this.temperatureLabel = createWeatherLabel("Température : --°C", Font.BOLD, 20, Color.BLACK);
        this.conditionLabel = createWeatherLabel("Condition : --", Font.PLAIN, 18, Color.DARK_GRAY);

        this.feelingLabel = createWeatherLabel("Ressenti : --", Font.PLAIN, 18, Color.GRAY);
        this.humidityLabel = createWeatherLabel("Humidité : --%", Font.PLAIN, 18, Color.BLUE);
        this.windLabel = createWeatherLabel("Vent : -- km/h", Font.PLAIN, 18, Color.BLUE);

        mainPanel.add(this.cityNameLabel);
        mainPanel.add(this.temperatureLabel);
        mainPanel.add(this.conditionLabel);
        mainPanel.add(this.feelingLabel);
        mainPanel.add(this.humidityLabel);
        mainPanel.add(this.windLabel);

        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        bottomPanel.setBackground(this.getBackground());

        this.iconLabel = new JLabel();
        this.iconLabel.setPreferredSize(new Dimension(120, 120));

        bottomPanel.add(this.iconLabel);

        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(this.getBackground());
        centerPanel.add(mainPanel, BorderLayout.CENTER);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private JLabel createWeatherLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", style, size));
        label.setForeground(color);
        return label;
    }

    public JTextField getCityField() {
        return cityField;
    }

    public void setCityField(JTextField cityField) {
        this.cityField = cityField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JLabel getTemperatureLabel() {
        return temperatureLabel;
    }

    public void setTemperatureLabel(JLabel temperatureLabel) {
        this.temperatureLabel = temperatureLabel;
    }

    public JLabel getConditionLabel() {
        return conditionLabel;
    }

    public void setConditionLabel(JLabel conditionLabel) {
        this.conditionLabel = conditionLabel;
    }

    public JLabel getHumidityLabel() {
        return humidityLabel;
    }

    public void setHumidityLabel(JLabel humidityLabel) {
        this.humidityLabel = humidityLabel;
    }

    public JLabel getWindLabel() {
        return windLabel;
    }

    public void setWindLabel(JLabel windLabel) {
        this.windLabel = windLabel;
    }

    public JLabel getCityNameLabel() {
        return cityNameLabel;
    }

    public void setCityNameLabel(JLabel cityNameLabel) {
        this.cityNameLabel = cityNameLabel;
    }

    public JLabel getFeelingLabel() {
        return feelingLabel;
    }

    public void setFeelingLabel(JLabel feelingLabel) {
        this.feelingLabel = feelingLabel;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public void setIconLabel(JLabel iconLabel) {
        this.iconLabel = iconLabel;
    }
    
}
