# Mortgage Calculator

## Overview
This Java application calculates monthly mortgage payments. It reads customer data from 'prospects.txt', including loan amounts, interest rates, and terms, and outputs each customer's monthly payment.

## Requirements
 - JDK 17
 - Maven

## Setup and Execution
1. **Compile the Project**
   - Run `mvn clean package` in the project directory to compile the project and create a JAR file.

2. **Build the Docker Image**
   - Run `docker build -t mortgage-calculator .` to build a Docker image using the included Dockerfile.

3. **Run the Application in Docker**
   - Execute `docker run --rm mortgage-calculator` to run the application in a Docker container.


## Running Tests
- Execute `mvn test` to run unit tests.

## Notes
 - The application expects data in `prospects.txt` with the format: `CustomerName,TotalLoan,Interest,Year`.
 - Commas within names should be enclosed in quotes.
 - Loan Terms: The application calculates the number of payments based on whole-year loan terms. Fractional years are not supported in the current implementation.