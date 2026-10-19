/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entite.Converter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Essotina
 */
public class ConverterService {
    
    private static final String API_KEY = "3fb42ca74b16f5acda1e40ab";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    
    private Map<String, Map<String, Double>> ratesCache = new HashMap<>();
    private Map<String, Long> cacheTimestamps = new HashMap<>();
    private static final long CACHE_DURATION = 3600000; 
    
    public Converter convertCurrency(String sourceCode, String targetCode, double amount) {
        try {
            // Récupération des taux de change
            Map<String, Double> rates = getExchangeRates(sourceCode);
            
            if (rates == null || !rates.containsKey(targetCode)) {
                System.err.println("Taux de change non disponible pour " + targetCode);
                return null;
            }
            
            // Récupération du taux de conversion
            double conversionRate = rates.get(targetCode);
            
            // Création de l'objet Converter
            Converter converter = new Converter();
            converter.setBaseCode(sourceCode);
            converter.setTargetCode(targetCode);
            converter.setConversionRate(conversionRate);
            converter.setAmount(amount);
            converter.setConversionRates(rates);
            
            // Calcul du résultat
            converter.calculateConversion();
            
            return converter;
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Map<String, Double> getExchangeRates(String baseCode) {
        try {
            // Vérification du cache
            if (isCacheValid(baseCode)) {
                System.out.println("Utilisation des taux en cache pour " + baseCode);
                return ratesCache.get(baseCode);
            }
            
            // Construction de l'URL
            String urlString = API_URL + API_KEY + "/latest/" + baseCode;
            
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
            Map<String, Double> rates = parseExchangeRates(response.toString());
            
            // Mise en cache
            if (rates != null) {
                ratesCache.put(baseCode, rates);
                cacheTimestamps.put(baseCode, System.currentTimeMillis());
            }
            
            return rates;
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des taux: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    private Map<String, Double> parseExchangeRates(String jsonData) {
        try {
            JsonObject json = JsonParser.parseString(jsonData).getAsJsonObject();
            
            // Vérification du résultat
            String result = json.get("result").getAsString();
            if (!"success".equals(result)) {
                System.err.println("Erreur API: " + json.get("error-type").getAsString());
                return null;
            }
            
            // Extraction des taux de conversion
            JsonObject conversionRates = json.getAsJsonObject("conversion_rates");
            Map<String, Double> rates = new HashMap<>();
            
            for (String key : conversionRates.keySet()) {
                rates.put(key, conversionRates.get(key).getAsDouble());
            }
            
            return rates;
            
        } catch (Exception e) {
            System.err.println("Erreur lors du parsing JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    private boolean isCacheValid(String baseCode) {
        if (!ratesCache.containsKey(baseCode) || !cacheTimestamps.containsKey(baseCode)) {
            return false;
        }
        
        long cacheAge = System.currentTimeMillis() - cacheTimestamps.get(baseCode);
        return cacheAge < CACHE_DURATION;
    }
    
    public String getLastUpdateTime(String baseCode) {
        try {
            String urlString = API_URL + API_KEY + "/latest/" + baseCode;
            
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
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
            
            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            
            if ("success".equals(json.get("result").getAsString())) {
                long timestamp = json.get("time_last_update_unix").getAsLong();
                return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
                        .format(new java.util.Date(timestamp * 1000));
            }
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération de la date: " + e.getMessage());
        }
        
        return "Inconnue";
    }
    
    public void clearCache() {
        ratesCache.clear();
        cacheTimestamps.clear();
        System.out.println("Cache des taux de change vidé");
    }
}
