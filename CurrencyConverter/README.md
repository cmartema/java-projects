# Currency Converter

This is a simple Java application that converts currency from one type to another using the CurrencyFreaks API.

## Features

- Converts currency from one type to another.
- Uses the CurrencyFreaks API to get the latest conversion rates.
- Supports multiple currency types including USD, AUD, CAD, PLN, MXN, EUR, JPY, GBP, and INR.

## Requirements

- Java 8 or higher
- json-simple-1.1.1.jar

## Setup

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run the application using the command [`java CurrencyConverter`](src/CurrencyConverter.java).

## Usage

1. When the application starts, it will display a list of supported currencies.
2. Enter the number corresponding to the currency you want to convert from.
3. Enter the number corresponding to the currency you want to convert to.
4. Enter the amount you want to convert.
5. The application will display the converted amount.

## Configuration

The application requires an API key from CurrencyFreaks to function. This should be provided in a `config.properties` file in the `src` directory. The file should contain the following:

```
API_KEY=your_api_key
```

Replace `your_api_key` with your actual API key from CurrencyFreaks.

## Dependencies

This project uses the following dependencies:

- json-simple-1.1.1.jar: Used for parsing JSON responses from the CurrencyFreaks API.
