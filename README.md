# Currency Converter

A real-time currency converter application built in Java that fetches live exchange rates from an external API.

---

## Features

- **Real-Time Exchange Rates**: Fetches current rates from ExchangeRate API
- **Multiple Currency Support**: Converts between 160+ world currencies
- **User-Friendly Interface**: Simple console-based interaction
- **Input Validation**: Handles invalid inputs and negative amounts
- **Accurate Conversions**: Displays results with proper formatting
- **Continuous Operation**: Convert multiple amounts without restarting

## Supported Currencies

Popular currencies include:
- USD (US Dollar), EUR (Euro), GBP (British Pound)
- JPY (Japanese Yen), INR (Indian Rupee)
- AUD (Australian Dollar), CAD (Canadian Dollar)
- CHF (Swiss Franc), CNY (Chinese Yuan)
- SGD (Singapore Dollar) and many more...

---

## How to Run

1. Ensure you have an active internet connection

2. Compile the program:
```Copy Code
   javac CurrencyConverter.java
```
3. Run the program:
```Copy Code
   java CurrencyConverter
```

Usage Example
```
Currency Converter
==================

Popular Currencies:
-------------------
USD - US Dollar          EUR - Euro
GBP - British Pound      JPY - Japanese Yen
INR - Indian Rupee       AUD - Australian Dollar

Enter base currency code (e.g., USD): USD
Enter target currency code (e.g., EUR): INR
Enter amount to convert: 100

--- Conversion Result ---
100.00 USD = 8325.50 INR
Exchange Rate: 1 USD = 83.2550 INR
-------------------------

Convert another amount? (yes/no): no

Thank you for using Currency Converter!
```
---

Technical Implementation
API Integration

API Used: ExchangeRate-API
Endpoint: https://api.exchangerate-api.com/v4/latest/{currency}
Method: HTTP GET requests

---

Key Components

HTTP Connection: Establishes connection with exchange rate API
JSON Parsing: Extracts rates using regex pattern matching
Error Handling: Manages network errors and invalid inputs
Input Validation: Ensures valid currency codes and amounts

---

Error Handling

Invalid currency codes
Network connection failures
Malformed user inputs
Negative amounts
API timeout (5 seconds)

---

Technologies Used

Java
HttpURLConnection (HTTP requests)
BufferedReader (response handling)
Regular Expressions (JSON parsing)
Scanner (user input)

---

Requirements

Java 8 or higher
Active internet connection
No external libraries required (uses Java standard library)

---

About
This project was developed as Task 4 during my Virtual Internship at CodSoft.
API Limitations

Free tier API with standard rate limits
Rates update every 24 hours
Requires internet connectivity

---

Future Enhancements

GUI implementation
Historical exchange rate data
Offline mode with cached rates
Multiple currency conversion at once
Currency trend graphs
