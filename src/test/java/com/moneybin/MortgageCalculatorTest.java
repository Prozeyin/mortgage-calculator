package com.moneybin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MortgageCalculatorTest {

    @Test
    public void testCalculateMonthlyPayment() {
        double principal = 1000.0;
        double annualInterestRate = 5.0;
        int years = 2;
        double expectedMonthlyPayment = 43.87;

        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(principal, annualInterestRate, years);

        assertEquals(expectedMonthlyPayment, monthlyPayment, 0.01, "The monthly payment should match the expected calculation.");
    }
    @Test
    void testWithNegativeValues() {
        assertThrows(IllegalArgumentException.class, () -> MortgageCalculator.calculateMonthlyPayment(-1000, 5, 10));
        assertThrows(IllegalArgumentException.class, () -> MortgageCalculator.calculateMonthlyPayment(1000, -5, 10));
        assertThrows(IllegalArgumentException.class, () -> MortgageCalculator.calculateMonthlyPayment(1000, 5, -10));
    }

    @Test
    void testWithZeroInterestRate() {
        assertThrows(IllegalArgumentException.class, () -> MortgageCalculator.calculateMonthlyPayment(1000, 0, 1));
    }

    @Test
    void testWithZeroYears() {
        assertThrows(IllegalArgumentException.class, () -> MortgageCalculator.calculateMonthlyPayment(1000, 5, 0));
    }
    @Test
    void testWithZeroLoan() {
        assertThrows(IllegalArgumentException.class, () -> MortgageCalculator.calculateMonthlyPayment(0, 5, 2));
    }
    @Test
    void testHighPrincipal() {
        assertDoesNotThrow(() -> MortgageCalculator.calculateMonthlyPayment(1e9, 5, 30));

        double expectedMonthlyPayment = 5368216.23;
        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(1e9, 5, 30);
        assertEquals(expectedMonthlyPayment, monthlyPayment, 0.01, "The monthly payment should match the expected calculation.");
    }
    @Test
    void testLongTerm() {
        assertDoesNotThrow(() -> MortgageCalculator.calculateMonthlyPayment(100000, 5, 50));
        double expectedMonthlyPayment = 454.14;
        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(100000, 5, 50);
        assertEquals(expectedMonthlyPayment, monthlyPayment, 0.01, "The monthly payment should match the expected calculation.");
    }
    @Test
    void testHighInterestRate() {
        assertDoesNotThrow(() -> MortgageCalculator.calculateMonthlyPayment(100000, 35, 20));
        double expectedMonthlyPayment = 2919.61;
        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(100000, 35, 20);
        assertEquals(expectedMonthlyPayment, monthlyPayment, 0.01, "The monthly payment should match the expected calculation.");
    }

    @Test
    void testBoundaryPrincipal() {
        assertDoesNotThrow(() -> MortgageCalculator.calculateMonthlyPayment(0.01, 5, 1));
        double expectedMonthlyPayment = 0.000856;
        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(0.01, 5, 1);
        assertEquals(expectedMonthlyPayment, monthlyPayment, 0.01, "The monthly payment should match the expected calculation.");
    }

    @Test
    void testBoundaryInterestRate() {
        assertDoesNotThrow(() -> MortgageCalculator.calculateMonthlyPayment(1000, 0.01, 1));

        double expectedMonthlyPayment = 83.34;
        double monthlyPayment = MortgageCalculator.calculateMonthlyPayment(1000, 0.01, 1);
        assertEquals(expectedMonthlyPayment, monthlyPayment, 0.01, "The monthly payment should match the expected calculation.");
    }

}
