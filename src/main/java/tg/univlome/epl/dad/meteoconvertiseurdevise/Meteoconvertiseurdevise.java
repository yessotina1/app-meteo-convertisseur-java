/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tg.univlome.epl.dad.meteoconvertiseurdevise;

import controller.ConverterController;
import controller.WeatherController;
import service.ConverterService;
import service.WeatherService;
import view.MainFrame;

/**
 *
 * @author Essotina
 */
public class Meteoconvertiseurdevise {

    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // Création de la fenêtre principale
                MainFrame frame = new MainFrame();
                
                // Création des services
                WeatherService weatherService = new WeatherService();
                ConverterService converterService = new ConverterService();
                
                // Création des contrôleurs et liaison avec les vues
                WeatherController weatherController = new WeatherController(
                    frame.getWeatherPanel(), 
                    weatherService
                );
                
                
                ConverterController converterController = new ConverterController(
                    frame.getConverterPanel(), 
                    converterService
                );

                    
                
                // Affichage de la fenêtre
                frame.setVisible(true);
                
                System.out.println("Application Météo & Convertisseur de Devises démarrée avec succès !");
                
            } catch (Exception e) {
                System.err.println("Erreur lors du démarrage de l'application: " + e.getMessage());
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Erreur lors du démarrage de l'application:\n" + e.getMessage(),
                    "Erreur de démarrage",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
    }
}
