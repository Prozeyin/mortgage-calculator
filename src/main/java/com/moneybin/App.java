package com.moneybin;

import java.io.IOException;

/**
 * The entry point for the Mortgage Calculator application.
 *
 * This application reads a file containing mortgage data and calculates the monthly repayment amount
 * for each customer listed in the file. It expects a file named "prospects.txt" in the resources directory.
 */
public class App {
    /**
     * The main method that starts the application.
     *
     * @param args The command line arguments. Currently, no command line arguments are necessary or expected.
     */
    public static void main(String[] args) throws IOException {
        // Process the mortgage data file named "prospects.txt".
       MortgageFileProcessor.processFile("prospects.txt");
    }
}