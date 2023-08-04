package com.example.demo7;

import javafx.animation.FadeTransition;
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

/**
 * Controller class for the JavaFX UI.
 */
public class HelloController implements Initializable {

    // FXML component declarations
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

    // Method executed during initialization
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Event handlers for image view transitions
        myImageViewEntry.setOnMouseClicked(this::changeImageExit);
        myImageViewExit.setOnMouseClicked(this::changeImageEntry);

        // Adding options to choice boxes
        From.getItems().addAll("USD", "EUR", "JPY", "BTC", "ETH", "USDT", "XRP", "BNB", "USDC", "DOGE", "ADA", "SOL", "TRX");
        To.getItems().addAll("USD", "EUR", "JPY", "BTC", "ETH", "USDT", "XRP", "BNB", "USDC", "DOGE", "ADA", "SOL", "TRX");

        // Other initialization code...

        // Event handler for closing the application
        closeIcon.setOnMouseClicked((mouseEvent -> System.exit(0)));

        // Adding options to temperature units choice boxes
        FromUnits.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        ToUnits.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");

        // Event handlers for showing/hiding panels
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

        // Fading animation for the result field update
        FadeTransition fadeResult = new FadeTransition(Duration.seconds(0.5), Result);
        fadeResult.setFromValue(1.0);
        fadeResult.setToValue(0.0);

        // Event handler for menu icon
        menu.setOnMouseClicked((event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.5), slider);

            if (slider.getTranslateX() != 0) {
                // If slider is not displayed, show it
                slide.setToX(0);
                closeIcon.setVisible(true);
                menu.setVisible(true);
            } else {
                // If slider is displayed, hide it
                slide.setToX(-260);
                closeIcon.setVisible(true);
                menu.setVisible(true);
            }

            slide.play();
        }));

        // Event handler for currency conversion result button
        resultButton.setOnMouseClicked(event -> {
            MoneyConversor conversor = new MoneyConversor(From.getValue(), To.getValue(), Double.parseDouble(Amount.getText()));
            String resultText = conversor.convertir();
            Result.setText("From: " + From.getValue() + " -" + " To: " + To.getValue() + "\nResult: " + resultText + " " + To.getValue());
        });

        // Event handler for unit conversion result button
        resultButtonUnits.setOnMouseClicked(event -> {
            UnitConversor conversor = new UnitConversor(FromUnits.getValue(), ToUnits.getValue(), Double.parseDouble(AmountUnits.getText()));
            String resultTextUnits = conversor.convert();
            ResultUnits.setText("From: " + FromUnits.getValue() + " -" + " To: " + ToUnits.getValue() + "\nResult: " + resultTextUnits + " " + ToUnits.getValue());
            fadeResult.play();
        });

        // Adding listeners for currency conversion
        Amount.textProperty().addListener((observable, oldValue, newValue) -> {
            convertCurrency();
        });

        From.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertCurrency();
        });

        To.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertCurrency();
        });

        // Adding listeners for temperature conversion
        AmountUnits.textProperty().addListener((observable, oldValue, newValue) -> {
            convertTemperature();
        });

        FromUnits.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertTemperature();
        });

        ToUnits.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            convertTemperature();
        });

        // Adding listener for bits conversion
        AmountBits.textProperty().addListener((observable, oldValue, newValue) -> {
            convertBits();
        });

        // Initializing the result fields with the initial conversions
        convertCurrency();
        convertTemperature();
        convertBits(); // Calling the method to convert bits automatically
    }

    // Other event handler methods...

    // Other private utility methods...

    // Method to change the entry image view
    public void changeImageEntry(MouseEvent event) {
        myImageViewExit.setVisible(false);
    }

    // Method to change the exit image view
    public void changeImageExit(MouseEvent event) {
        myImageViewExit.setVisible(true);
    }

    // Method to handle currency conversion
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

    // Method to handle temperature conversion
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

    // Method to handle bits conversion
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
