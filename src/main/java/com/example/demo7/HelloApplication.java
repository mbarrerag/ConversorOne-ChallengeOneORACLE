package com.example.demo7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane; // or import the appropriate Pane class
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class for the JavaFX application.
 */
public class HelloApplication extends Application {

    /**
     * The entry point of the JavaFX application.
     * Initializes and shows the main application window.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        AnchorPane root = fxmlLoader.load();

        // Set the maximum width of the root pane to 1300
        root.setMinWidth(1300); // Optional: You can set the minimum width as well to maintain a fixed width.
        root.setMinHeight(900); // Optional: You can set the minimum height as well to maintain a fixed height.

        Scene scene = new Scene(root, 1070, 900);
        stage.setTitle("ConversorOne!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}
