package src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Properties;

//json-simple-1.1.1.jar

public class CurrencyConverter {

    // Define a global variable to store the API key
    public static String YOUR_ACCESS_KEY; 

    private static void sendHttpGetRequest(String fromCurrency, 
                                                String toCurrency, double convertedAmount, 
                                                    double amount) throws IOException, org.json.simple.parser.ParseException {

        // Send a GET request to the API to get the conversion rate
        // Convert the amount using the conversion rate

        String GET_URL = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=" + 
        YOUR_ACCESS_KEY +"&base=" + fromCurrency + "&symbols=" + toCurrency;

        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
        }in.close();

        // Parse the JSON response and get the conversion rate
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(response.toString());
        JSONObject rates = (JSONObject) obj.get("rates");
        String rate = (String) rates.get(toCurrency);
        Double exchangeRate = Double.valueOf(rate);  
        convertedAmount = amount * exchangeRate;
        System.out.println("Converted amount: " + convertedAmount + " " + toCurrency);

        } else { // failure
        System.out.println("GET request failed!");
        }
    }
    public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException {

        Properties props = new Properties();
        
        try {
            //load a properties file from class path, inside static method
            props.load(new FileInputStream("CurrencyConverter/src/config.properties"));
        } catch (IOException e) {
           System.out.println("Error: config.properties file not found");
           System.exit(1);
        }

        //get the property value and print it out
        YOUR_ACCESS_KEY = props.getProperty("API_KEY");
        
        HashMap<Integer, String> CurrencyCodes = new HashMap<Integer, String>();

        // Adding currency codes to the HashMap
        CurrencyCodes.put(1, "USD"); // United States Dollar
        CurrencyCodes.put(2, "AUD"); // Australian Dollar
        CurrencyCodes.put(3, "CAD"); // Canadian Dollar
        CurrencyCodes.put(4, "PLN"); // Polish Zloty
        CurrencyCodes.put(5, "MXN"); // Mexican Peso
        CurrencyCodes.put(6, "EUR"); // Euro
        CurrencyCodes.put(7, "JPY"); // Japanese Yen
        CurrencyCodes.put(8, "GBP"); // British Pound Sterling
        CurrencyCodes.put(9, "INR"); // Indian Rupee

        String fromCurrency, toCurrency;
        double amount, convertedAmount;
        amount= convertedAmount = 0.0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter!");
        System.out.println(" 1. USD (United States Dollar) \n 2. AUD (Australian Dollar) \n 3. CAD (Canadian Dollar) \n " +
                                    "4. PLN (Polish Zloty) \n 5. MXN (Mexican Peso) \n 6. EUR (Euro) \n 7. JPY (Japanese Yen) \n " +
                                    "8. GBP (British Pound Sterling) \n 9. INR (Indian Rupee)");
        System.out.println("Enter the currency you want to convert FROM:");
        

        fromCurrency = CurrencyCodes.get(sc.nextInt());

        System.out.println("Enter the currency you want to convert TO:");

        toCurrency = CurrencyCodes.get(sc.nextInt());

        System.out.println("Enter the amount you want to convert:");
        amount = sc.nextDouble();

        
        System.out.println("Amount to convert: " + amount + " " + fromCurrency);
        sendHttpGetRequest(fromCurrency, toCurrency, convertedAmount, amount);
        System.out.println("Thank you for using Currency Converter!");
        sc.close();
    }
}