package com.example.demo7;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

/**
 * A class that performs currency conversion.
 */
public class MoneyConversor {

    // Exchange rates matrix
    double[][] tasasDeCambio = { /* ... */ };

    // Currency codes
    String[] codigosMoneda = { /* ... */ };

    private String monedaDestino;
    private String monedaOrigen;
    private double cantidad;

    // Constructor
    public MoneyConversor(String monedaDestino, String monedaOrigen, double cantidad) {
        this.monedaDestino = monedaDestino;
        this.monedaOrigen = monedaOrigen;
        this.cantidad = cantidad;
    }

    // Perform currency conversion
    public String convertir() {
        int indiceMonedaOrigen = obtenerIndiceMoneda(monedaOrigen, codigosMoneda);
        int indiceMonedaDestino = obtenerIndiceMoneda(monedaDestino, codigosMoneda);

        if (indiceMonedaOrigen == -1 || indiceMonedaDestino == -1) {
            return null;
        }

        double tasaCambioOrigenADolar = tasasDeCambio[indiceMonedaOrigen][0];
        double tasaCambioDestinoADolar = tasasDeCambio[indiceMonedaDestino][0];

        double cantidadEnDolares = cantidad / tasaCambioOrigenADolar;
        double cantidadConvertida = cantidadEnDolares * tasaCambioDestinoADolar;

        // Format the converted amount with a maximum of 2 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String cantidadConvertidaFormateada = decimalFormat.format(cantidadConvertida);

        return cantidadConvertidaFormateada;
    }

    // Utility method to obtain the index of a currency
    private static int obtenerIndiceMoneda(String moneda, String[] codigosMoneda) {
        for (int i = 0; i < codigosMoneda.length; i++) {
            if (codigosMoneda[i].equals(moneda)) {
                return i;
            }
        }
        return -1; // Currency not found
    }
}
