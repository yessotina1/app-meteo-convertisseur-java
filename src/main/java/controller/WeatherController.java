/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.WeatherService;

/**
 *
 * @author Essotina
 */
import view.WeatherPanel;

public class WeatherController {
    private WeatherPanel view;
    private WeatherService service;

    public WeatherController(WeatherPanel view, WeatherService service) {
        this.view = view;
        this.service = service;

        // Écouteur sur le bouton Rechercher
        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshWeather();
            }
        });
    }

    private void refreshWeather() {
        String city = view.getCityField().getText();
        if (city == null || city.isEmpty()) {
            view.getCityNameLabel().setText("Veuillez entrer une ville");
            return;
        }

        // Appel simple au service météo
        var weather = service.getWeatherByCity(city, "metric", "fr");

        if (weather != null) {
            view.getCityNameLabel().setText(city.toUpperCase());
            view.getTemperatureLabel().setText("Température : " + weather.getTemperature() + " °C");
            view.getConditionLabel().setText("Condition : " + weather.getDescription());
            view.getHumidityLabel().setText("Humidité : " + weather.getHumidity() + "%");
            view.getWindLabel().setText("Vent : " + weather.getWindSpeed() + " km/h");
            view.getFeelingLabel().setText("Ressenti : " + weather.getFeelsLike() + " °C");
        } else {
            view.getCityNameLabel().setText("Ville non trouvée");
        }
    }
}
