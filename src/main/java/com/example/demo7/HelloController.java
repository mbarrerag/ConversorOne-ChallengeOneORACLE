package com.example.demo7;import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ImageView myImageViewEntry;
    @FXML
    private ImageView myImageViewExit;
    @FXML
    private ImageView closeIcon;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane slider;

    @FXML
    private TextField Amount;
    @FXML
    private TextField AmountUnits;
    @FXML
    private TextField AmountBits;
    @FXML
    private Label Result;
    @FXML
    private Label ResultUnits;
    @FXML
    private Label ResultBits;
    @FXML
    private ChoiceBox<String> From;
    @FXML
    private ChoiceBox<String> To;
    @FXML
    private ChoiceBox<String> FromUnits;
    @FXML
    private ChoiceBox<String> ToUnits;
    @FXML
    private Button resultButton;
    @FXML
    private Button resultButtonUnits;
    @FXML
    private Button resultButtonBits;
    @FXML
    private Button coins;
    @FXML
    private Button units;
    @FXML
    private Button bits;
    @FXML
    private AnchorPane panelCoin;
    @FXML
    private AnchorPane panelUnits;
    @FXML
    private AnchorPane panelBits;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myImageViewEntry.setOnMouseClicked(this::changeImageExit);
        myImageViewExit.setOnMouseClicked(this::changeImageEntry);

        From.getItems().addAll("USD", "EUR", "JPY", "BTC", "ETH", "USDT", "XRP", "BNB", "USDC", "DOGE", "ADA", "SOL", "TRX");
        To.getItems().addAll("USD", "EUR", "JPY", "BTC", "ETH", "USDT", "XRP", "BNB", "USDC", "DOGE", "ADA", "SOL", "TRX");


        closeIcon.setOnMouseClicked((mouseEvent -> System.exit(0)));

        FromUnits.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        ToUnits.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");

        coins.setOnMouseClicked(event -> {
            panelUnits.setVisible(false);
            panelBits.setVisible(false);
        });

        units.setOnMouseClicked(event -> {
            panelUnits.setVisible(true);
            panelBits.setVisible(false);
        });

        bits.setOnMouseClicked(event -> {
            panelBits.setVisible(true);
            panelUnits.setVisible(false);
        });

        // Add fading animation for the result text field update
        FadeTransition fadeResult = new FadeTransition(Duration.seconds(0.5), Result);
        fadeResult.setFromValue(1.0);
        fadeResult.setToValue(0.0);
        menu.setOnMouseClicked((event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.5), slider);

            if (slider.getTranslateX() != 0) {
                // Si el slider no está desplegado, mostrarlo
                slide.setToX(0);
                closeIcon.setVisible(true);
                menu.setVisible(true);
            } else {
                // Si el slider está desplegado, ocultarlo
                slide.setToX(-260);
                closeIcon.setVisible(true);
                menu.setVisible(true);
            }

            slide.play();
        }));

        resultButton.setOnMouseClicked(event -> {
            MoneyConversor conversor = new MoneyConversor(From.getValue(), To.getValue(), Double.parseDouble(Amount.getText()));
            String resultText = conversor.convertir();
            Result.setText("From: " + From.getValue() + " -" + " To: " + To.getValue() + "\nResult: " + resultText + " " + To.getValue());
        });

        resultButtonUnits.setOnMouseClicked(event -> {
            UnitConversor conversor = new UnitConversor(FromUnits.getValue(), ToUnits.getValue(), Double.parseDouble(AmountUnits.getText()));
            String resultTextUnits = conversor.convert();
            ResultUnits.setText("From: " + FromUnits.getValue() + " -" + " To: " + ToUnits.getValue() + "\nResult: " + resultTextUnits + " " + ToUnits.getValue());
            fadeResult.play();
        });

        // As before, set up listeners for the currency conversion
        Amount.textProperty().addListener((observable, oldValue, newValue) -> {
            convertCurrency();
        });

        From.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertCurrency();
        });

        To.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertCurrency();
        });

        // Add listeners for the temperature conversion
        AmountUnits.textProperty().addListener((observable, oldValue, newValue) -> {
            convertTemperature();
        });

        FromUnits.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertTemperature();
        });

        ToUnits.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertTemperature();
        });

        AmountBits.textProperty().addListener((observable, oldValue, newValue) -> {
            convertBits();
        });

        // Initialize the result fields with the initial conversions
        convertCurrency();
        convertTemperature();
        convertBits(); // Call the method to convert bits automatically
    }

    public void changeImageEntry(MouseEvent event) {
        myImageViewExit.setVisible(false);
    }

    public void changeImageExit(MouseEvent event) {
        myImageViewExit.setVisible(true);
    }

    private void convertCurrency() {
        try {
            if (From.getValue() != null && To.getValue() != null && Amount.getText() != null) {
                MoneyConversor conversor = new MoneyConversor(From.getValue(), To.getValue(), Double.parseDouble(Amount.getText()));
                String resultText = conversor.convertir();
                Result.setText("From: " + From.getValue() + " -" + " To: " + To.getValue() + "\nResult: " + resultText + " " + To.getValue());
            }
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric amount)
            Result.setText("");
        }
    }

    private void convertTemperature() {
        try {
            if (FromUnits.getValue() != null && ToUnits.getValue() != null && AmountUnits.getText() != null) {
                UnitConversor conversor = new UnitConversor(FromUnits.getValue(), ToUnits.getValue(), Double.parseDouble(AmountUnits.getText()));
                String resultTextUnits = conversor.convert();
                ResultUnits.setText("From: " + FromUnits.getValue() + " -" + " To: " + ToUnits.getValue() + "\nResult: " + resultTextUnits + " " + ToUnits.getValue());
            }
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric temperature)
            ResultUnits.setText("");
        }
    }

    private void convertBits() {
        try {
            if (AmountBits.getText() != null) {
                BitsConversor conversor = new BitsConversor(Double.parseDouble(AmountBits.getText()));
                String resultTextBits = conversor.convertToBinary();
                ResultBits.setText("Amount: " + AmountBits.getText() + "\nResult: " + resultTextBits);
            }
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric value)
            ResultBits.setText("");
        }
    }
}
