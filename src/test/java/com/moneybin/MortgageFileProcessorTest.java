package com.moneybin;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
public class MortgageFileProcessorTest {

    @Test
    public void validFileTest(){
        assertDoesNotThrow(() -> MortgageFileProcessor.processFile("valid.txt"));
    }
    @Test
    public void invalidFileTest(){
        assertThrows(IOException.class, () ->  MortgageFileProcessor.processFile("invalid.txt"));
    }

    @Test
    void malformedFileTest() {
        // Redirect System.err to a ByteArrayOutputStream
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            // Act: Call the method that processes the file
            MortgageFileProcessor.processFile("malformed.txt");

            // Make sure to flush the PrintStream to capture all data
            System.err.flush();

            // Parse the captured content
            String actualOutput = errContent.toString();

            // Assertions to check if specific error messages are present
            String expectedNumericValueError = "Expected a numeric value";
            String expectedIntegerValueError = "Expected an integer value";
            long numericErrors = actualOutput.lines().filter(line -> line.contains(expectedNumericValueError)).count();
            long integerErrors = actualOutput.lines().filter(line -> line.contains(expectedIntegerValueError)).count();

            // Assert that the expected error messages are present
            assertTrue(numericErrors == 2, "Expected numeric value errors were not found.");
            assertTrue(integerErrors == 1, "Expected integer value errors were not found.");

            // Additional assertions can be added here

        } catch (IOException e) {
            fail("An IOException was not expected to be thrown.", e);
        } finally {
            // Reset System.err to its original stream
            System.setErr(originalErr);

            // Display all captured messages for debugging purposes
            System.out.println("Captured error messages:");
            System.out.println(errContent.toString());
        }
    }

}
