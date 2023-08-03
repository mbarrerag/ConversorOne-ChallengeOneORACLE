package com.example.demo7;

import java.text.DecimalFormat;

public class UnitConversor {

    double[][] conversionMatrix = {
            // Celsius   Fahrenheit   Kelvin
            {1.0,       33.8,        274.15}, // Celsius
            {-17.2222,  1.0,         255.372}, // Fahrenheit
            {272.15,    457.87,      1.0}      // Kelvin
    };

    String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

    private String unitFrom;
    private String unitTo;
    private double temperature;

    public UnitConversor(String unitFrom, String unitTo, double temperature) {
        this.unitFrom = unitFrom;
        this.unitTo = unitTo;
        this.temperature = temperature;
    }

    public String convert() {
        int indexFrom = getUnitIndex(unitFrom, units);
        int indexTo = getUnitIndex(unitTo, units);

        if (indexFrom == -1 || indexTo == -1) {
            return null;
        }

        double celsiusValue = convertToCelsius();
        double convertedTemperature = convertFromCelsius(celsiusValue, indexTo);

        // Format the converted temperature with a maximum of 2 decimals
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(convertedTemperature);
    }

    private double convertToCelsius() {
        int indexFrom = getUnitIndex(unitFrom, units);
        if (indexFrom == 0) {
            // Already in Celsius
            return temperature;
        } else if (indexFrom == 1) {
            // Convert Fahrenheit to Celsius
            return (temperature - 32) * 5 / 9;
        } else {
            // Convert Kelvin to Celsius
            return temperature - 273.15;
        }
    }

    private double convertFromCelsius(double celsiusValue, int indexTo) {
        if (indexTo == 0) {
            // Convert Celsius to Celsius
            return celsiusValue;
        } else if (indexTo == 1) {
            // Convert Celsius to Fahrenheit
            return (celsiusValue * 9 / 5) + 32;
        } else {
            // Convert Celsius to Kelvin
            return celsiusValue + 273.15;
        }
    }

    private static int getUnitIndex(String unit, String[] units) {
        for (int i = 0; i < units.length; i++) {
            if (units[i].equals(unit)) {
                return i;
            }
        }
        return -1; // Unit not found
    }
}
