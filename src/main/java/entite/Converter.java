/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.util.Map;

/**
 *
 * @author Essotina
 */
public class Converter {
    private String baseCode;        // Code de la devise de base
    private String targetCode;      // Code de la devise cible
    private double conversionRate;  // Taux de conversion
    private double amount;          // Montant à convertir
    private double result;          // Résultat de la conversion
    private String lastUpdate; 
    private Map<String, Double> conversionRates;

    public Converter() {
    }
    
    

    public Converter(String baseCode, String targetCode, double conversionRate, double amount, double result, String lastUpdate) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.conversionRate = conversionRate;
        this.amount = amount;
        this.result = result;
        this.lastUpdate = lastUpdate;
    }

    public Converter(String baseCode, String targetCode, double conversionRate, double amount, double result, String lastUpdate, Map<String, Double> conversionRates) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.conversionRate = conversionRate;
        this.amount = amount;
        this.result = result;
        this.lastUpdate = lastUpdate;
        this.conversionRates = conversionRates;
    }

    
    
    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public void calculateConversion() {
        this.result = this.amount * this.conversionRate;
    }
    
    
    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    
    
}
