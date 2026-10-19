/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.ConverterService;
import view.ConverterPanel;

/**
 *
 * @author Essotina
 */
public class ConverterController {
    
    
    private ConverterPanel view;
    private ConverterService service;

    // Supprimez le premier constructeur sans service
    public ConverterController(ConverterPanel view, ConverterService service) {
        this.view = view;
        this.service = service;
        initController();
    }

    private void initController() {
        // Écouteur sur le bouton Convertir
        this.view.getConvertButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });
    }

    private void convert() {
        try {
            double amount = Double.parseDouble(view.getAmountField().getText());
            
            // Récupérer les codes de devise sélectionnés
            String sourceCode = view.getSelectedSourceCurrencyCode();
            String targetCode = view.getSelectedTargetCurrencyCode();
            
            // Utiliser le service pour convertir
            entite.Converter converter = service.convertCurrency(sourceCode, targetCode, amount);
            
            if (converter != null) {
                // Le montant converti devrait être dans le converter
                // Selon votre classe Converter, utilisez la méthode appropriée
                double convertedAmount = converter.getAmount() * converter.getConversionRate();
                
                // Formater le résultat
                String resultText = String.format("%.2f %s = %.2f %s", 
                        amount, sourceCode, convertedAmount, targetCode);
                
                view.getResultLabel().setText(resultText);
                
                // Mettre à jour la date de mise à jour
                String updateTime = service.getLastUpdateTime(sourceCode);
                view.getUpdateLabel().setText("Dernière mise à jour: " + updateTime);
                
            } else {
                view.getResultLabel().setText("Erreur: Service indisponible");
                view.getUpdateLabel().setText("Vérifiez votre connexion internet");
            }

        } catch (NumberFormatException e) {
            view.getResultLabel().setText("Erreur: Montant invalide");
        } catch (Exception e) {
            view.getResultLabel().setText("Erreur lors de la conversion");
            e.printStackTrace();
        }
    }
}
