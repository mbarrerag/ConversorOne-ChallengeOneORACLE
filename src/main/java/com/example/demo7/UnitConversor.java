package com.example.demo7;

import java.text.DecimalFormat;

public class UnitConversor {

    // Conversion matrix for temperature units
    double[][] conversionMatrix = {
            // Celsius   Fahrenheit   Kelvin
            // Conversion values for different units
    };

    // Available temperature units
    String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

    private String unitFrom;
    private String unitTo;
    private double temperature;

    // Constructor to initialize unit conversion parameters
    public UnitConversor(String unitFrom, String unitTo, double temperature) {
        this.unitFrom = unitFrom;
        this.unitTo = unitTo;
        this.temperature = temperature;
    }

    // Method to perform unit conversion
    public String convert() {
        int indexFrom = getUnitIndex(unitFrom, units);
        int indexTo = getUnitIndex(unitTo, units);

        // Return null if units are not found
        if (indexFrom == -1 || indexTo == -1) {
            return null;
        }

        // Convert temperature to Celsius for conversion
        double celsiusValue = convertToCelsius();
        // Convert Celsius value to the desired unit
        double convertedTemperature = convertFromCelsius(celsiusValue, indexTo);

        // Format the converted temperature with a maximum of 2 decimals
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(convertedTemperature);
    }

    // Convert input temperature to Celsius
    private double convertToCelsius() {
        int indexFrom = getUnitIndex(unitFrom, units);
        if (indexFrom == 0) {
            // Temperature is already in Celsius
            return temperature;
        } else if (indexFrom == 1) {
            // Convert Fahrenheit to Celsius
            return (temperature - 32) * 5 / 9;
        } else {
            // Convert Kelvin to Celsius
            return temperature - 273.15;
        }
    }

    // Convert temperature from Celsius to the desired unit
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

    // Utility method to obtain the index of a unit from the array
    private static int getUnitIndex(String unit, String[] units) {
        for (int i = 0; i < units.length; i++) {
            if (units[i].equals(unit)) {
                return i;
            }
        }
        return -1; // Unit not found
    }
}
