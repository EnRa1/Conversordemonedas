
package conversordemonedas1;


import javax.swing.JOptionPane;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Conversordemonedas1 {

    private static final String API_KEY = "aa50bba297563e4bffba41cb"; // Reemplaza con tu clave de API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        while (true) {
            // Menu principal
            String[] options = {"Conversor de Moneda"};
            String mainMenuChoice = (String) JOptionPane.showInputDialog(null, 
                    "Seleccione una opción", 
                    "Menu Principal", 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    options, 
                    options[0]);
            
            if (mainMenuChoice == null) {
                JOptionPane.showMessageDialog(null, "Programa Terminado");
                break;
            }

            if (mainMenuChoice.equals("Conversor de Moneda")) {
                // Opciones de monedas
                String[] currencies = {
                        "USD a EUR",
                        "EUR a USD",
                        "USD a COP",
                        "COP a USD",
                        "USD a JPY",
                        "JPY a USD",
                        "USD a GBP",
                        "GBP a USD",
                        "EUR a GBP",
                        "GBP a EUR",
                        "EUR a JPY",
                        "JPY a EUR",
                        "COP a EUR",
                        "EUR a COP",
                        "COP a JPY",
                        "JPY a COP"
                        // Agrega más conversiones según sea necesario
                };
                String currencyChoice = (String) JOptionPane.showInputDialog(null, 
                        "Seleccione la conversión de moneda", 
                        "Opciones de Moneda", 
                        JOptionPane.PLAIN_MESSAGE, 
                        null, 
                        currencies, 
                        currencies[0]);
                
                if (currencyChoice == null) {
                    continue;
                }

                // Entrada de valor
                String inputValue = JOptionPane.showInputDialog(null, 
                        "Ingrese la cantidad de dinero que desea convertir", 
                        "Entrada de Valor", 
                        JOptionPane.PLAIN_MESSAGE);
                
                if (inputValue == null) {
                    continue;
                }

                // Validación del valor ingresado
                double amount = 0;
                try {
                    amount = Double.parseDouble(inputValue);
                    if (amount < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor no válido. Por favor ingrese un número positivo.");
                    continue;
                }

                // Conversión de moneda usando la API
                double convertedValue = 0;
                String[] currencyPair = currencyChoice.split(" a ");
                String baseCurrency = currencyPair[0];
                String targetCurrency = currencyPair[1];

                try {
                    double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
                    convertedValue = amount * exchangeRate;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al obtener la tasa de cambio: " + e.getMessage());
                    continue;
                }

                // Mostrar valor de la conversión
                JOptionPane.showMessageDialog(null, 
                        "El valor de la conversión es: " + convertedValue);

                // ¿Desea continuar?
                int response = JOptionPane.showConfirmDialog(null, 
                        "¿Desea continuar?", 
                        "Continuar", 
                        JOptionPane.YES_NO_CANCEL_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.NO_OPTION || response == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "Programa Terminado");
                    break;
                }
            }
        }
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            JSONParser parser = new JSONParser();
            JSONObject dataObject = (JSONObject) parser.parse(stringBuilder.toString());
            JSONObject conversionRates = (JSONObject) dataObject.get("conversion_rates");
            return (double) conversionRates.get(targetCurrency);
        }
    }
}
