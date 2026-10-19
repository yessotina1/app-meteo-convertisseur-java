/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Essotina
 */
public class MainFrame extends JFrame{
    private ConverterPanel converterPanel;
    private WeatherPanel weatherPanel;
    
    public MainFrame(){
        this.setTitle("Convertisseur de Devises & Météo en Temps Réel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setResizable(true);
        this.setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setContentPane(mainPanel);
        
        this.weatherPanel = new WeatherPanel();
        this.converterPanel = new ConverterPanel();

        mainPanel.add(this.weatherPanel, BorderLayout.NORTH);
        mainPanel.add(this.converterPanel, BorderLayout.CENTER);
        
    }

    
    
    public ConverterPanel getConverterPanel() {
        return converterPanel;
    }

    public void setConverterPanel(ConverterPanel converterPanel) {
        this.converterPanel = converterPanel;
    }

    public WeatherPanel getWeatherPanel() {
        return weatherPanel;
    }

    public void setWeatherPanel(WeatherPanel weatherPanel) {
        this.weatherPanel = weatherPanel;
    }
    
    
    
    
    
}
