package com.example.demo7;
import java.text.DecimalFormat;

public class BitsConversor {

    private double number;

    public BitsConversor(double number) {
        this.number = number;
    }

    public String convertToBinary() {
        int intPart = (int) number;
        double fractionalPart = number - intPart;

        String binaryIntPart = convertIntToBinary(intPart);
        String binaryFractionalPart = convertFractionalToBinary(fractionalPart);

        return binaryIntPart + "." + binaryFractionalPart;
    }

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
