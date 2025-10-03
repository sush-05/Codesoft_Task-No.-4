import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CurrencyConverter {
    
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Currency Converter");
        System.out.println("==================\n");
        
        boolean continueConverting = true;
        
        while (continueConverting) {
            displayAvailableCurrencies();
            
            String baseCurrency = getBaseCurrency();
            String targetCurrency = getTargetCurrency();
            double amount = getAmount();
            
            if (amount >= 0) {
                double rate = fetchExchangeRate(baseCurrency, targetCurrency);
                
                if (rate > 0) {
                    double result = amount * rate;
                    displayResult(baseCurrency, targetCurrency, amount, result, rate);
                } else {
                    System.out.println("Failed to fetch exchange rate. Please try again.\n");
                }
            }
            
            continueConverting = askToContinue();
        }
        
        System.out.println("\nThank you for using Currency Converter!");
        scanner.close();
    }
    
    private static void displayAvailableCurrencies() {
        System.out.println("Popular Currencies:");
        System.out.println("-------------------");
        System.out.println("USD - US Dollar          EUR - Euro");
        System.out.println("GBP - British Pound      JPY - Japanese Yen");
        System.out.println("INR - Indian Rupee       AUD - Australian Dollar");
        System.out.println("CAD - Canadian Dollar    CHF - Swiss Franc");
        System.out.println("CNY - Chinese Yuan       SGD - Singapore Dollar");
        System.out.println("And many more...\n");
    }
    
    private static String getBaseCurrency() {
        System.out.print("Enter base currency code (e.g., USD): ");
        return scanner.nextLine().toUpperCase().trim();
    }
    
    private static String getTargetCurrency() {
        System.out.print("Enter target currency code (e.g., EUR): ");
        return scanner.nextLine().toUpperCase().trim();
    }
    
    private static double getAmount() {
        while (true) {
            try {
                System.out.print("Enter amount to convert: ");
                double amount = Double.parseDouble(scanner.nextLine().trim());
                
                if (amount < 0) {
                    System.out.println("Amount cannot be negative.\n");
                    continue;
                }
                
                return amount;
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
            }
        }
    }
    
    private static double fetchExchangeRate(String base, String target) {
        try {
            String urlString = API_URL + base;
            URL url = new URL(urlString);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int responseCode = connection.getResponseCode();
            
            if (responseCode != 200) {
                System.out.println("Error: Unable to fetch rates. Check currency codes.");
                return -1;
            }
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );
            
            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            String jsonResponse = response.toString();
            double rate = extractRate(jsonResponse, target);
            
            return rate;
            
        } catch (Exception e) {
            System.out.println("Error connecting to exchange rate service.");
            return -1;
        }
    }
    
    private static double extractRate(String json, String currency) {
        try {
            String searchPattern = "\"" + currency + "\":(\\d+\\.?\\d*)";
            Pattern pattern = Pattern.compile(searchPattern);
            Matcher matcher = pattern.matcher(json);
            
            if (matcher.find()) {
                return Double.parseDouble(matcher.group(1));
            }
            
            return -1;
            
        } catch (Exception e) {
            return -1;
        }
    }
    
    private static void displayResult(String from, String to, double amount, 
                                     double result, double rate) {
        System.out.println("\n--- Conversion Result ---");
        System.out.printf("%.2f %s = %.2f %s\n", amount, from, result, to);
        System.out.printf("Exchange Rate: 1 %s = %.4f %s\n", from, rate, to);
        System.out.println("-------------------------\n");
    }
    
    private static boolean askToContinue() {
        System.out.print("Convert another amount? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        System.out.println();
        
        return response.equals("yes") || response.equals("y");
    }
}