/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Essotina
 */
public class ConverterPanel extends JPanel{
    private JComboBox<String> sourceDevise;
    private JComboBox<String> targetDevise;
    private JTextField amountField;
    private JLabel resultLabel;
    private JButton convertButton;
    
    // Label pour afficher la dernière mise à jour des taux
    private JLabel updateLabel;
    
    public ConverterPanel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
                "Convertisseur de Devises"
        ));
        this.setBackground(new Color(240, 255, 240));

        this.initComponents();
    }
    
    public void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        JLabel titleLabel = new JLabel("Conversion de Devises", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 100, 0));
        this.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel sourceLabel = new JLabel("De:");
        sourceLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        this.add(sourceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        this.sourceDevise = new JComboBox<>(this.getCurrencyListWithNames());
        this.sourceDevise.setSelectedItem("EUR - Euro");
        this.sourceDevise.setFont(new Font("Arial", Font.PLAIN, 14));
        this.sourceDevise.setPreferredSize(new Dimension(250, 40));
        this.add(this.sourceDevise, gbc);
        
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        JLabel targetLabel = new JLabel("À:");
        targetLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(targetLabel, gbc);

        gbc.gridx = 4;
        gbc.gridwidth = 2;
        this.targetDevise = new JComboBox<>(this.getCurrencyListWithNames());
        this.targetDevise.setSelectedItem("XOF - Franc CFA (BCEAO)");
        this.targetDevise.setFont(new Font("Arial", Font.PLAIN, 14));
        this.targetDevise.setPreferredSize(new Dimension(250, 40));
        this.add(this.targetDevise, gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        JLabel amountLabel = new JLabel("Montant:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
        this.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 4;
        this.amountField = new JTextField("1.00");
        this.amountField.setFont(new Font("Arial", Font.PLAIN, 18)); 
        this.amountField.setHorizontalAlignment(JTextField.RIGHT);

        // Validation pour n'autoriser que les nombres et un point décimal
        this.amountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = ConverterPanel.this.amountField.getText();

                // Autorise seulement les chiffres, le point et les touches de contrôle
                if (!Character.isDigit(c)
                        && c != '.'
                        && c != KeyEvent.VK_BACK_SPACE
                        && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }

                // Empêche d'avoir plus d'un point décimal
                if (c == '.' && text.contains(".")) {
                    e.consume();
                }
            }
        });

        this.add(this.amountField, gbc);

        // Bouton de conversion
        gbc.gridy = 3;
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        this.convertButton = new JButton("Convertir");
        this.convertButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        this.convertButton.setBackground(new Color(50, 205, 50));
        this.convertButton.setForeground(Color.WHITE);
        this.convertButton.setPreferredSize(new Dimension(180, 45));
        this.add(this.convertButton, gbc);

        // Label pour afficher le résultat de la conversion
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        this.resultLabel = new JLabel("Résultat: 0.00", SwingConstants.CENTER);
        this.resultLabel.setFont(new Font("Arial", Font.BOLD, 22));
        this.resultLabel.setForeground(new Color(0, 128, 0)); // Vert
        this.resultLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        this.add(this.resultLabel, gbc);

        // Label pour afficher la dernière mise à jour des taux
        gbc.gridy = 5;
        this.updateLabel = new JLabel("Taux de change non chargés", SwingConstants.CENTER);
        this.updateLabel.setFont(new Font("Arial", Font.ITALIC, 13));
        this.updateLabel.setForeground(Color.GRAY);
        this.add(this.updateLabel, gbc);
    }
    
    /**
     * Retourne Tableau de chaînes au format "CODE - Nom"
     **/
    private String[] getCurrencyListWithNames() {
        return new String[]{
            "AED - Dirham des Émirats arabes unis",
            "AFN - Afghani afghan",
            "ALL - Lek albanais",
            "AMD - Dram arménien",
            "ANG - Florin des Antilles néerlandaises",
            "AOA - Kwanza angolais",
            "ARS - Peso argentin",
            "AUD - Dollar australien",
            "AWG - Florin arubais",
            "AZN - Manat azerbaïdjanais",
            "BAM - Mark convertible de Bosnie-Herzégovine",
            "BBD - Dollar barbadien",
            "BDT - Taka bangladais",
            "BGN - Lev bulgare",
            "BHD - Dinar bahreïni",
            "BIF - Franc burundais",
            "BMD - Dollar bermudien",
            "BND - Dollar brunéien",
            "BOB - Boliviano bolivien",
            "BRL - Réal brésilien",
            "BSD - Dollar bahaméen",
            "BTN - Ngultrum bhoutanais",
            "BWP - Pula botswanais",
            "BYN - Rouble biélorusse",
            "BZD - Dollar bélizien",
            "CAD - Dollar canadien",
            "CDF - Franc congolais",
            "CHF - Franc suisse",
            "CLF - Unité de compte chilienne",
            "CLP - Peso chilien",
            "CNH - Renminbi offshore chinois",
            "CNY - Renminbi chinois",
            "COP - Peso colombien",
            "CRC - Colón costaricain",
            "CUP - Peso cubain",
            "CVE - Escudo cap-verdien",
            "CZK - Couronne tchèque",
            "DJF - Franc djiboutien",
            "DKK - Couronne danoise",
            "DOP - Peso dominicain",
            "DZD - Dinar algérien",
            "EGP - Livre égyptienne",
            "ERN - Nakfa érythréen",
            "ETB - Birr éthiopien",
            "EUR - Euro",
            "FJD - Dollar fidjien",
            "FKP - Livre des îles Falkland",
            "FOK - Couronne féroïenne",
            "GBP - Livre sterling",
            "GEL - Lari géorgien",
            "GGP - Livre de Guernesey",
            "GHS - Cédi ghanéen",
            "GIP - Livre de Gibraltar",
            "GMD - Dalasi gambien",
            "GNF - Franc guinéen",
            "GTQ - Quetzal guatémaltèque",
            "GYD - Dollar guyanien",
            "HKD - Dollar de Hong Kong",
            "HNL - Lempira hondurien",
            "HRK - Kuna croate",
            "HTG - Gourde haïtienne",
            "HUF - Forint hongrois",
            "IDR - Roupie indonésienne",
            "ILS - Shekel israélien",
            "IMP - Livre de l'île de Man",
            "INR - Roupie indienne",
            "IQD - Dinar irakien",
            "IRR - Rial iranien",
            "ISK - Couronne islandaise",
            "JEP - Livre de Jersey",
            "JMD - Dollar jamaïcain",
            "JOD - Dinar jordanien",
            "JPY - Yen japonais",
            "KES - Shilling kényan",
            "KGS - Som kirghize",
            "KHR - Riel cambodgien",
            "KID - Dollar kiribatien",
            "KMF - Franc comorien",
            "KRW - Won sud-coréen",
            "KWD - Dinar koweïtien",
            "KYD - Dollar des îles Caïmans",
            "KZT - Tenge kazakh",
            "LAK - Kip laotien",
            "LBP - Livre libanaise",
            "LKR - Roupie srilankaise",
            "LRD - Dollar libérien",
            "LSL - Loti lesothan",
            "LYD - Dinar libyen",
            "MAD - Dirham marocain",
            "MDL - Leu moldave",
            "MGA - Ariary malgache",
            "MKD - Denar macédonien",
            "MMK - Kyat birman",
            "MNT - Tugrik mongol",
            "MOP - Pataca macanaise",
            "MRU - Ouguiya mauritanien",
            "MUR - Roupie mauricienne",
            "MVR - Rufiyaa maldivien",
            "MWK - Kwacha malawite",
            "MXN - Peso mexicain",
            "MYR - Ringgit malaisien",
            "MZN - Metical mozambicain",
            "NAD - Dollar namibien",
            "NGN - Naira nigérian",
            "NIO - Córdoba nicaraguayen",
            "NOK - Couronne norvégienne",
            "NPR - Roupie népalaise",
            "NZD - Dollar néo-zélandais",
            "OMR - Rial omanais",
            "PAB - Balboa panaméen",
            "PEN - Sol péruvien",
            "PGK - Kina papou-néo-guinéen",
            "PHP - Peso philippin",
            "PKR - Roupie pakistanaise",
            "PLN - Zloty polonais",
            "PYG - Guaraní paraguayen",
            "QAR - Riyal qatari",
            "RON - Leu roumain",
            "RSD - Dinar serbe",
            "RUB - Rouble russe",
            "RWF - Franc rwandais",
            "SAR - Riyal saoudien",
            "SBD - Dollar des îles Salomon",
            "SCR - Roupie seychelloise",
            "SDG - Livre soudanaise",
            "SEK - Couronne suédoise",
            "SGD - Dollar de Singapour",
            "SHP - Livre de Sainte-Hélène",
            "SLE - Leone sierra-léonais",
            "SOS - Shilling somalien",
            "SRD - Dollar surinamais",
            "SSP - Livre sud-soudanaise",
            "STN - Dobra de São Tomé-et-Príncipe",
            "SYP - Livre syrienne",
            "SZL - Lilangeni swazi",
            "THB - Baht thaïlandais",
            "TJS - Somoni tadjik",
            "TMT - Manat turkmène",
            "TND - Dinar tunisien",
            "TOP - Paʻanga tongien",
            "TRY - Lire turque",
            "TTD - Dollar de Trinité-et-Tobago",
            "TVD - Dollar tuvaluan",
            "TWD - Dollar taïwanais",
            "TZS - Shilling tanzanien",
            "UAH - Hryvnia ukrainienne",
            "UGX - Shilling ougandais",
            "USD - Dollar américain",
            "UYU - Peso uruguayen",
            "UZS - Sum ouzbek",
            "VES - Bolívar vénézuélien",
            "VND - Dồng vietnamien",
            "VUV - Vatu vanuatais",
            "WST - Tala samoan",
            "XAF - Franc CFA (BEAC)",
            "XCD - Dollar des Caraïbes orientales",
            "XDR - Droits de tirage spéciaux (FMI)",
            "XOF - Franc CFA (BCEAO)",
            "XPF - Franc CFP",
            "YER - Rial yéménite",
            "ZAR - Rand sud-africain",
            "ZMW - Kwacha zambien",
            "ZWL - Dollar zimbabwéen"
        };
    }
    
     // Extrait le code de devise à partir de l'élément sélectionné du ComboBox
     
    public String extractCurrencyCode(String selected) {
        if (selected == null || selected.isEmpty()) {
            return "";
        }
        // Divise la chaîne au niveau du tiret et prend la première partie
        String[] parts = selected.split(" - ");
        return parts[0].trim();
    }

    //Extrait le nom de devise à partir de l'élément sélectionné du ComboBox
    public String extractCurrencyName(String selected) {
        if (selected == null || selected.isEmpty()) {
            return "";
        }
        // Divise la chaîne au niveau du tiret et prend la deuxième partie
        String[] parts = selected.split(" - ");
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return parts[0].trim();
    }

    //Récupère le code de la devise source sélectionnée
    public String getSelectedSourceCurrencyCode() {
        String selected = (String) sourceDevise.getSelectedItem();
        return extractCurrencyCode(selected);
    }

    //Récupère le code de la devise cible sélectionnée
    public String getSelectedTargetCurrencyCode() {
        String selected = (String) targetDevise.getSelectedItem();
        return extractCurrencyCode(selected);
    }

    // Récupère le nom de la devise source sélectionnée
    public String getSelectedSourceCurrencyName() {
        String selected = (String) sourceDevise.getSelectedItem();
        return extractCurrencyName(selected);
    }

    public JComboBox<String> getSourceCurrencyCombo() {
        return sourceDevise;
    }

    public void setSourceCurrencyCombo(JComboBox<String> sourceCurrencyCombo) {
        this.sourceDevise = sourceCurrencyCombo;
    }

    public JComboBox<String> getTargetCurrencyCombo() {
        return targetDevise;
    }

    public void setTargetCurrencyCombo(JComboBox<String> targetCurrencyCombo) {
        this.targetDevise = targetCurrencyCombo;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public void setAmountField(JTextField amountField) {
        this.amountField = amountField;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(JLabel resultLabel) {
        this.resultLabel = resultLabel;
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public void setConvertButton(JButton convertButton) {
        this.convertButton = convertButton;
    }

    public JLabel getUpdateLabel() {
        return updateLabel;
    }

    public void setUpdateLabel(JLabel updateLabel) {
        this.updateLabel = updateLabel;
    }
    
    
    
}
