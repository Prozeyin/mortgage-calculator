package com.moneybin;

/**
 * Calculates the monthly payments required to pay off a loan based on the loan amount,
 * annual interest rate, and terms of the loan.
 */
public class MortgageCalculator {

    /**
     * Calculates the monthly payment required to pay off a loan.
     *
     * @param principal          the total amount of the loan
     * @param annualInterestRate the annual interest rate in percentage
     * @param years              the term of the loan in years
     * @return the monthly payment amount
     */

    public static double calculateMonthlyPayment(double principal, double annualInterestRate, int years) {
        if (principal <= 0 || annualInterestRate <= 0 || years <= 0) {
            throw new IllegalArgumentException("Loan amount, interest rate, and loan term must be positive numbers.");
        }
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        // Note: The loan term in years is assumed to be in whole numbers. Fractional years (e.g., 1.5 years) are not supported.
        int numberOfPayments = years * 12;
        double monthlyPayment =
                (principal * monthlyInterestRate) /
                        (1 - (1 / power(1 + monthlyInterestRate, numberOfPayments))); // math.pow is part of java.lang So I believe it should be allowed, if it is, Replace this line with: (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

        return monthlyPayment;
    }

    /**
     * A manual implementation of exponentiation, to replace Math.pow.
     * It handles negative exponents by computing the reciprocal of the base raised to the absolute value of the exponent.
     * Note that this method does not handle fractional exponents.
     *
     * @param base     The base value.
     * @param exponent The exponent value.
     * @return The result of raising the base to the power of the exponent.
     */
    public static double power(double base, int exponent) {
        if (exponent == 0) return 1; // Any number to the power of 0 is 1.
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        double result = 1;
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        return result;
    }
}