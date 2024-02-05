package com.moneybin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Processes mortgage data from a file and calculates the monthly payments for each customer entry.
 * The file is expected to be in CSV format with each line containing a customer's data.
 */
public class MortgageFileProcessor {

    /**
     * Reads mortgage data from the specified file and calculates the monthly payment for each customer.
     * Each line in the file should contain the customer's name, total loan amount, annual interest rate, and loan period in years.
     *
     * @param resourceFileName The name of the file containing the mortgage data.
     */
    public static void processFile(String resourceFileName) throws IOException {
        InputStream is = MortgageFileProcessor.class.getResourceAsStream("/" + resourceFileName);
        if (is == null) {
            throw new IOException("Resource file not found: " + resourceFileName);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            // Skip the header line of the CSV.
            String line = reader.readLine();
            int prospectNumber = 1;
            int lineNumber = 1;  // tracking line number for better error messages.
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    // Split the line into data fields. Fields with internal commas are handled by the regex.
                    String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    if (data.length < 4) {
                        // If the line does not have the correct format, it's logged and skipped.
                        System.err.println("Skipping malformed line (expected 4 fields, found " + data.length + "): " + line);
                        continue;
                    }

                    // Validate and process each data field.
                    String customerName = extractCustomerName(data[0]);
                    double loanAmount = parseDoubleField(data[1], "loan amount", lineNumber);
                    double interestRate = parseDoubleField(data[2], "interest rate", lineNumber);
                    int loanTermYears = parseIntField(data[3], "loan term", lineNumber);

                    // Calculate and print the monthly payment for the customer.
                    double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(loanAmount, interestRate, loanTermYears);
                    System.out.printf("Prospect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month%n",
                            prospectNumber++, customerName, loanAmount, loanTermYears, monthlyPayment);
                } catch (IllegalArgumentException e) {
                    // Log an error if the input data is invalid.
                    System.err.println("Error processing line: " + line + " - " + e.getMessage());
                }
            }
        }
    }
    /**
     * Extracts the customer's name from the CSV data field, removing any surrounding quotes and internal commas.
     *
     * @param dataField The CSV field containing the customer's name.
     * @return The customer's name without quotes or internal commas.
     */
    private static String extractCustomerName(String dataField) {
        // Currently, the code assumes the comma in a name field enclosed in quotes represents a single individual's name.
        // If the comma represents two different individuals, the following logic would need to be adjusted.
        // customerName = customerName.replace(",", ""); <-- this line would be removed.
        String customerName = dataField;
        if (customerName.startsWith("\"") && customerName.endsWith("\"")) {
            customerName = customerName.substring(1, customerName.length() - 1); // Remove surrounding quotes.
            customerName = customerName.replace(",", " "); // Remove internal commas.
        }
        return customerName;
    }

    /**
     * Parses a string field into a double.
     * Throws an exception with a detailed message if the string cannot be parsed.
     *
     * @param field       The string to parse.
     * @param fieldName   The name of the field being parsed; used for the error message.
     * @param lineNumber  The line number from the CSV file where the field is located; used for the error message.
     * @return The parsed double value.
     * @throws IllegalArgumentException If the field cannot be parsed as a double.
     */
    private static double parseDoubleField(String field, String fieldName, int lineNumber) {
        try {
            return Double.parseDouble(field);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Expected a numeric value for %s on line %d, but found: '%s'", fieldName, lineNumber, field));
        }
    }

    /**
     * Parses a string field into an integer.
     * Throws an exception with a detailed message if the string cannot be parsed.
     *
     * @param field       The string to parse.
     * @param fieldName   The name of the field being parsed; used for the error message.
     * @param lineNumber  The line number from the CSV file where the field is located; used for the error message.
     * @return The parsed integer value.
     * @throws IllegalArgumentException If the field cannot be parsed as an integer.
     */
    private static int parseIntField(String field, String fieldName, int lineNumber) {
        try {
            return Integer.parseInt(field);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Expected an integer value for %s on line %d, but found: '%s'", fieldName, lineNumber, field));
        }
    }
}
