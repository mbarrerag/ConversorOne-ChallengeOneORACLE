package com.example.demo7;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class MoneyConversor {



    double[][] tasasDeCambio = {
            // USD    EUR     JPY     BTC      ETH       USDT       XRP      BNB      USDC      DOGE      ADA       SOL      TRX
            {1.0,   0.89,   0.00691,  0.000034, 0.000540,  0.000034, 0.000024, 0.000004, 0.000034, 0.000267, 0.000011, 0.000833, 0.000028}, // USD
            {0.92,  1.09,   0.0064,   0.000032, 0.000510,  0.000032, 0.000023, 0.000004, 0.000032, 0.000253, 0.000010, 0.000767, 0.000026}, // EUR
            {143,   156,    1.0,      0.0049,   0.0779,    0.0049,   0.0035,   0.0006,   0.0049,   0.0385,   0.0016,   0.1226,   0.0041},   // JPY
            {29365, 32833, 4498395,  1.0,      15.9,      1.0,      0.711,    0.120,    1.0,      7.84,     0.320,    24.33,    0.816},   // BTC
            {1859,  2080,  284706,   0.062,    1.0,       0.062,    0.044,    0.007,    0.062,    0.488,    0.020,    1.49,     0.050},   // ETH
            {1,     1.12,  0.016,    0.0000000224, 0.000000351, 1.0,      0.00071,  0.00012,  1.0,      0.00784,  0.00032,  0.02433,  0.00081},  // USDT
            {1393,  1559,  213640,   0.0007,   0.0113,    0.0007,   1.0,      0.167,    0.0007,   0.0055,   0.0002,   0.3981,   0.0134},   // XRP
            {26067, 29169, 3992045,  0.0042,   0.0671,    0.0042,   0.00298,  1.0,      0.0042,   0.0329,   0.0014,   1.059,    0.0355},   // BNB
            {1,     1.12,  153.45,   0.0000000412, 0.000000649, 0.0000000412, 0.0000291, 0.0000049, 1.0,      0.00784,  0.00032,  0.02433,  0.00081},  // USDC
            {12647, 14174, 1940368,  0.000127, 0.00202,    0.000127, 0.0000896, 0.0000151, 0.000127, 1.0,      0.000041, 0.3118,   0.0104},   // DOGE
            {87610, 97992, 13403647, 0.0000101, 0.000159,  0.0000101, 0.0000071, 0.0000012, 0.0000101, 0.0000793, 1.0,      0.7191,   0.0241},   // ADA
            {120,   134,   18391,    0.0000412, 0.000649,  0.0000412, 0.0000291, 0.0000049, 0.0000412, 0.000323,  0.000013, 1.0,      0.0334},   // SOL
            {11892, 13311, 1824493,  0.0000338, 0.000534,  0.0000338, 0.0000240, 0.0000041, 0.0000338, 0.000265,  0.000011, 0.806,    1.0}       // TRX
    };

    String[] codigosMoneda = {"USD", "EUR", "JPY", "BTC", "ETH", "USDT", "XRP", "BNB", "USDC", "DOGE", "ADA", "SOL", "TRX"};

    private String monedaDestino;
    private String monedaOrigen;
    private double cantidad;

    public MoneyConversor(String monedaDestino, String monedaOrigen, double cantidad) {

        this.monedaDestino = monedaDestino;
        this.monedaOrigen = monedaOrigen;
        this.cantidad = cantidad;
    }

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

        // Formatear cantidadConvertida con máximo 2 decimales
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String cantidadConvertidaFormateada = decimalFormat.format(cantidadConvertida);

            return decimalFormat.format(cantidadConvertida);
         }

    private static int obtenerIndiceMoneda(String moneda, String[] codigosMoneda) {
        for (int i = 0; i < codigosMoneda.length; i++) {
            if (codigosMoneda[i].equals(moneda)) {
                return i;
            }
        }
        return -1; // Moneda no encontrada
    }
}
