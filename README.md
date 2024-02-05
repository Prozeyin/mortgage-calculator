# Mortgage Calculator

## Overview
This Java application calculates monthly mortgage payments. It reads customer data from 'prospects.txt', including loan amounts, interest rates, and terms, and outputs each customer's monthly payment.

## Requirements
 - JDK 17
 - Maven

## Setup and Execution
1. **Compile the Projects**
    - Run `mvn compile` in the project directory.
2. **Run the Application**
   - Execute `java -cp target/classes com.moneybin.App` to start the application
## Running Tests
- Execute `mvn test` to run unit tests.
## Notes
 - The application expects data in `prospects.txt` with the format: `CustomerName,TotalLoan,Interest,Year`.
 - Commas within names should be enclosed in quotes.
 - Loan Terms: The application calculates the number of payments based on whole-year loan terms. Fractional years are not supported in the current implementation.