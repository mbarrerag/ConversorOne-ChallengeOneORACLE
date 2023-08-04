package com.example.demo7;

import java.text.DecimalFormat;

/**
 * A utility class for converting decimal numbers to binary representation.
 */
public class BitsConversor {

    private double number;

    /**
     * Constructs a BitsConversor object with the specified decimal number.
     *
     * @param number The decimal number to be converted.
     */
    public BitsConversor(double number) {
        this.number = number;
    }

    /**
     * Converts the specified decimal number to its binary representation.
     *
     * @return The binary representation of the decimal number.
     */
    public String convertToBinary() {
        int intPart = (int) number;
        double fractionalPart = number - intPart;

        String binaryIntPart = convertIntToBinary(intPart);
        String binaryFractionalPart = convertFractionalToBinary(fractionalPart);

        return binaryIntPart + "." + binaryFractionalPart;
    }

    /**
     * Converts an integer to its binary representation.
     *
     * @param number The integer to be converted.
     * @return The binary representation of the integer.
     */
    private String convertIntToBinary(int number) {
        StringBuilder binaryBuilder = new StringBuilder();
        if (number == 0) {
            binaryBuilder.append("0");
        } else {
            while (number > 0) {
                binaryBuilder.append(number % 2);
                number /= 2;
            }
        }
        return binaryBuilder.reverse().toString();
    }

    /**
     * Converts the fractional part of a decimal number to its binary representation.
     *
     * @param fractional The fractional part of the decimal number.
     * @return The binary representation of the fractional part.
     */
    private String convertFractionalToBinary(double fractional) {
        StringBuilder binaryBuilder = new StringBuilder();
        int maxIterations = 8; // Consider up to 8 decimal places

        while (fractional > 0 && maxIterations > 0) {
            fractional *= 2;
            binaryBuilder.append((int) fractional);
            fractional -= (int) fractional;
            maxIterations--;
        }

        return binaryBuilder.toString();
    }
}
