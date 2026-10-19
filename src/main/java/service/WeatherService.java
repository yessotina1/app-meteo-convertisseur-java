/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entite.Weather;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author Essotina
 */
public class WeatherService {
    
    private static final String API_KEY = "59599e43cbc05fdecc43d4c66a8b069d";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    
    
    public Weather getWeatherByCity(String city, String units, String lang) {
        try {
            // Construction de l'URL avec paramètres
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
            String urlString = String.format("%s?q=%s&units=%s&lang=%s&appid=%s",
                    API_URL, encodedCity, units, lang, API_KEY);
            
            // Création de la connexion HTTP
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            // Vérification du code de réponse
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.err.println("Erreur HTTP: " + responseCode);
                return null;
            }
            
            // Lecture de la réponse
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)
            );
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            
            // Parsing de la réponse JSON
            return parseWeatherData(response.toString());
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des données météo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    private Weather parseWeatherData(String jsonData) {
        try {
            JsonObject json = JsonParser.parseString(jsonData).getAsJsonObject();
            
            // Extraction des données principales
            JsonObject main = json.getAsJsonObject("main");
            JsonObject wind = json.getAsJsonObject("wind");
            JsonObject weather = json.getAsJsonArray("weather").get(0).getAsJsonObject();
            
            double temperature = main.get("temp").getAsDouble();
            double feelsLike = main.get("feels_like").getAsDouble();
            int humidity = main.get("humidity").getAsInt();
            double windSpeed = wind.get("speed").getAsDouble();
            String description = weather.get("description").getAsString();
            String icon = weather.get("icon").getAsString();
            
            // Conversion de la vitesse du vent de m/s à km/h
            windSpeed = windSpeed * 3.6;
            
            return new Weather(temperature, feelsLike, humidity, windSpeed, description, icon);
            
        } catch (Exception e) {
            System.err.println("Erreur lors du parsing JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
